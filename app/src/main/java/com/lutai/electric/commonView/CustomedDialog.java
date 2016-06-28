package com.lutai.electric.commonView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lutai.electric.R;
import com.lutai.electric.utils.CommonUtil;

/**
 * 
 * <p>
 * 自定义对话框
 * </p>
 * 
 * @author 刘佳宽 研发中心 2013年7月11日11:12:33
 */
public class CustomedDialog extends Dialog {

	public static final int MODE_NATION = 1; // 自定义组件
	public static final int MODE_WARNING = 2; // 警告
	public static final int MODE_ALERT = 3; // 提示

	
	public static CustomedDialog getInstance(final Context context, String msg,
											 String positiveString, OnClickListener onPositiveClickListener) {
		CustomedDialog.Builder customBuilder = new CustomedDialog.Builder(
				context);
		customBuilder.setMessage(msg).setPositiveButton(positiveString,
				onPositiveClickListener);
		CustomedDialog dialog = customBuilder.create(true);
		dialog.show();
		return dialog;
	}

	/**
	 * 创建对话框实例，并且显示
	 */
	public static CustomedDialog getInstance(final Context context, String msg) {
		CustomedDialog.Builder customBuilder = new CustomedDialog.Builder(
				context);
		customBuilder.setMessage(msg);
		CustomedDialog dialog = customBuilder.create(true);
		dialog.show();
		return dialog;
	}

	public static CustomedDialog getInstance(final Context context, String msg,
											 View icon) {
		CustomedDialog.Builder customBuilder = new CustomedDialog.Builder(
				context);
		customBuilder.setMessage(msg).setContentView(icon);
		CustomedDialog dialog = customBuilder.create(true);
		return dialog;
	}

	/**
	 * 根据MODE来判断得到的对话框的样式
	 *
	 * @param context
	 * @param msg
	 * @param icon
	 * @param mode
	 * @return
	 */
	public static CustomedDialog getInstance(final Context context, String msg,
											 View icon, int mode, String positiveString,
											 OnClickListener onPositiveClickListener, String negativeButtonText,
											 OnClickListener onNegativeButtonListener, boolean isLineShow) {
		CustomedDialog.Builder customBuilder = new CustomedDialog.Builder(
				context);
		customBuilder
				.setMessage(msg)
				.setContentView(icon)
				.setMode(mode)
				.setPositiveButton(positiveString, onPositiveClickListener)
				.setNegativeButton(negativeButtonText, onNegativeButtonListener);
		CustomedDialog dialog = customBuilder.create(isLineShow);
		return dialog;
	}

	// 自定义dialog
	public static CustomedDialog getInstance(final Context context, String msg,
											 String title, View contentView, String positiveString,
											 OnClickListener onPositiveClickListener, boolean isLineShow) {
		CustomedDialog.Builder customBuilder = new CustomedDialog.Builder(
				context);
		customBuilder.setMessage(msg).setContentView(contentView)
				.setTitle(title)
				.setPositiveButton(positiveString, onPositiveClickListener);
		CustomedDialog dialog = customBuilder.create(isLineShow);
		return dialog;
	}

	public CustomedDialog(Context context, int theme) {
		super(context, theme);
	}

	public CustomedDialog(Context context) {
		this(context, 0);
	}

	/**
	 * Helper class for creating a custom dialog
	 */
	public static class Builder {

		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private int mode = 0; // default
		private View contentView;

		private DialogInterface.OnClickListener positiveButtonClickListener,
				negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		public Builder setMode(int mode) {
			this.mode = mode;
			return this;
		}

		public Builder setPositiveButton(int positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * Create the custom dialog
		 */
		public CustomedDialog create(boolean isShow) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final CustomedDialog dialog = new CustomedDialog(context,
					R.style.Dialog);
			View layout;
			if (CommonUtil.isEmpty(message)) {
				layout = inflater.inflate(R.layout.common_nation_dialog, null);
				if (isShow) {
					// layout.findViewById(R.id.deliverone).setVisibility(
					// View.VISIBLE);
					// layout.findViewById(R.id.delivertwo).setVisibility(
					// View.VISIBLE);
				}
			} else {
				if (mode == CustomedDialog.MODE_ALERT) {
					layout = inflater.inflate(R.layout.common_alert_dialog,
							null);
				} else {
					layout = inflater.inflate(R.layout.common_warning_dialog,
							null);
				}
			}
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			// set the dialog title
			if (title != null) {
				((TextView) layout.findViewById(R.id.title))
						.setVisibility(View.VISIBLE);
				((TextView) layout.findViewById(R.id.title)).setText(title);
			} else {
				((TextView) layout.findViewById(R.id.title))
						.setVisibility(View.GONE);
			}
			// set the confirm button
			boolean isHidePositive = false;
			boolean isHideNegative = false;
			if (positiveButtonText != null) {
				Button posiButton = ((Button) layout
						.findViewById(R.id.positive));
				posiButton.setText(positiveButtonText);
				posiButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						dialog.dismiss();
						if (positiveButtonClickListener != null) {
							positiveButtonClickListener.onClick(dialog,
									DialogInterface.BUTTON_POSITIVE);
						}
					}
				});
			} else {
				isHidePositive = true;
				layout.findViewById(R.id.positive).setVisibility(View.GONE);
			}
			if (negativeButtonText != null) {
				Button negaButton = ((Button) layout
						.findViewById(R.id.negative));
				negaButton.setText(negativeButtonText);
				negaButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						dialog.dismiss();
						if (negativeButtonClickListener != null) {
							negativeButtonClickListener.onClick(dialog,
									DialogInterface.BUTTON_NEGATIVE);
						}
					}
				});
			} else {
				isHideNegative = true;
				layout.findViewById(R.id.negative).setVisibility(View.GONE);
			}

			if (isHideNegative && isHidePositive) {
				layout.findViewById(R.id.btn_layout).setVisibility(View.GONE);
				// if (layout.findViewById(R.id.delivertwo) != null) {
				// layout.findViewById(R.id.delivertwo).setVisibility(
				// View.GONE);
				// }
			} else {
				layout.findViewById(R.id.btn_layout)
						.setVisibility(View.VISIBLE);
				// layout.findViewById(R.id.delivertwo)
				// .setVisibility(View.VISIBLE);
			}

			if (message != null) {
				TextView msgView = ((TextView) layout
						.findViewById(R.id.message));
				msgView.setVisibility(View.VISIBLE);
				msgView.setText(message);
			}
			if (contentView != null) {
				LinearLayout linearLayout = ((LinearLayout) layout
						.findViewById(R.id.content));
				linearLayout.setVisibility(View.VISIBLE);
				linearLayout.addView(contentView);
			}
			return dialog;
		}
	}

}
