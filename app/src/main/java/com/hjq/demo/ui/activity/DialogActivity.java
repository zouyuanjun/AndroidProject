package com.hjq.demo.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.hjq.base.BaseDialog;
import com.hjq.base.BaseDialogFragment;
import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.ui.dialog.ShareDialog;
import com.hjq.demo.ui.dialog.UpdateDialog;
import com.hjq.dialog.AddressDialog;
import com.hjq.dialog.DateDialog;
import com.hjq.dialog.InputDialog;
import com.hjq.dialog.MenuDialog;
import com.hjq.dialog.MessageDialog;
import com.hjq.dialog.PayPasswordDialog;
import com.hjq.dialog.ToastDialog;
import com.hjq.dialog.WaitDialog;
import com.hjq.umeng.Platform;
import com.hjq.umeng.UmengClient;
import com.hjq.umeng.UmengShare;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/12/02
 *    desc   : 对话框使用案例
 */
public final class DialogActivity extends MyActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    protected int getTitleId() {
        return R.id.tb_dialog_title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

//    BaseDialog mWaitDialog;

    @OnClick({R.id.btn_dialog_message, R.id.btn_dialog_input, R.id.btn_dialog_bottom_menu, R.id.btn_dialog_center_menu,
            R.id.btn_dialog_succeed_toast, R.id.btn_dialog_fail_toast, R.id.btn_dialog_warn_toast, R.id.btn_dialog_wait,
            R.id.btn_dialog_pay, R.id.btn_dialog_address, R.id.btn_dialog_date, R.id.btn_dialog_update,
            R.id.btn_dialog_share, R.id.btn_dialog_custom})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_message: // 消息对话框
                new MessageDialog.Builder(this)
                        .setTitle("我是标题") // 标题可以不用填写
                        .setMessage("我是内容")
                        .setConfirm("确定")
                        .setCancel("取消") // 设置 null 表示不显示取消按钮
                        //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                        .setListener(new MessageDialog.OnListener() {

                            @Override
                            public void onConfirm(Dialog dialog) {
                                toast("确定了");
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_input: // 输入对话框
                new InputDialog.Builder(this)
                        .setTitle("我是标题") // 标题可以不用填写
                        .setContent("我是内容")
                        .setHint("我是提示")
                        .setConfirm("确定")
                        .setCancel("取消") // 设置 null 表示不显示取消按钮
                        //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                        .setListener(new InputDialog.OnListener() {

                            @Override
                            public void onConfirm(Dialog dialog, String content) {
                                toast("确定了：" + content);
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_bottom_menu: // 底部选择框
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    data.add("我是数据" + i);
                }
                new MenuDialog.Builder(this)
                        .setCancel("取消") // 设置 null 表示不显示取消按钮
                        //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                        .setList(data)
                        .setListener(new MenuDialog.OnListener() {

                            @Override
                            public void onSelected(Dialog dialog, int position, String text) {
                                toast("位置：" + position + "，文本：" + text);
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("取消了");
                            }
                        })
                        .setGravity(Gravity.BOTTOM)
                        .setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
                        .show();
                break;
            case R.id.btn_dialog_center_menu: // 居中选择框
                List<String> data1 = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    data1.add("我是数据" + i);
                }
                new MenuDialog.Builder(this)
                        .setCancel(null) // 设置 null 表示不显示取消按钮
                        //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                        .setList(data1)
                        .setListener(new MenuDialog.OnListener() {

                            @Override
                            public void onSelected(Dialog dialog, int position, String text) {
                                toast("位置：" + position + "，文本：" + text);
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("取消了");
                            }
                        })
                        .setGravity(Gravity.CENTER)
                        .setAnimStyle(BaseDialog.AnimStyle.SCALE)
                        .show();
                break;
            case R.id.btn_dialog_succeed_toast: // 成功对话框
                new ToastDialog.Builder(this)
                        .setType(ToastDialog.Type.FINISH)
                        .setMessage("完成")
                        .show();
                break;
            case R.id.btn_dialog_fail_toast: // 失败对话框
                new ToastDialog.Builder(this)
                        .setType(ToastDialog.Type.ERROR)
                        .setMessage("错误")
                        .show();
                break;
            case R.id.btn_dialog_warn_toast: // 警告对话框
                new ToastDialog.Builder(this)
                        .setType(ToastDialog.Type.WARN)
                        .setMessage("警告")
                        .show();
                break;
            case R.id.btn_dialog_wait: // 等待对话框
                final BaseDialog dialog = new WaitDialog.Builder(this)
                        .setMessage("加载中...") // 消息文本可以不用填写
                        .show();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 3000);

                /*
                if(mWaitDialog == null){
                    mWaitDialog = new WaitDialog.Builder(this)
                            .create();
                }
                mWaitDialog.show();

                postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mWaitDialog.dismiss();
                    }
                }, 10000);
                */
                break;
            case R.id.btn_dialog_pay: // 支付密码输入对话框
                new PayPasswordDialog.Builder(this)
                        .setTitle("请输入支付密码")
                        .setSubTitle("用于购买一个女盆友")
                        .setMoney("￥ 100.00")
                        //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                        .setListener(new PayPasswordDialog.OnListener() {

                            @Override
                            public void onCompleted(Dialog dialog, String password) {
                                toast(password);
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_address: // 选择地区对话框
                new AddressDialog.Builder(this)
                        .setTitle("选择地区")
                        //.setProvince("广东省") // 设置默认省份
                        //.setCity("广州市") // 设置默认城市（必须要先设置默认省份）
                        //.setIgnoreArea() // 不选择县级区域
                        .setListener(new AddressDialog.OnListener() {

                            @Override
                            public void onSelected(Dialog dialog, String province, String city, String area) {
                                toast(province + city + area);
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_date: // 日期选择对话框
                new DateDialog.Builder(this)
                        .setTitle("请选择日期")
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(Dialog dialog, int year, int month, int day) {
                                toast(year + "年" + month + "月" + day + "日");
                            }

                            @Override
                            public void onCancel(Dialog dialog) {
                                toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_share:
                new ShareDialog.Builder(this)
                        .setShareTitle("分享标题")
                        .setShareDescription("分享描述")
                        .setShareUrl("https://github.com/getActivity/AndroidProject")
                        .setShareLogo("https://www.baidu.com/img/bd_logo1.png")
                        .setListener(new UmengShare.OnShareListener() {

                            @Override
                            public void onSucceed(Platform platform) {
                                toast("分享成功");
                            }

                            @Override
                            public void onError(Platform platform, Throwable t) {
                                toast("分享出错");
                            }

                            @Override
                            public void onCancel(Platform platform) {
                                toast("分享取消");
                            }
                        })
                        .show();
                break;
            case R.id.btn_dialog_update:
                try {
                    // 本地的版本码和服务器的进行比较
                    if (20 > getPackageManager().getPackageInfo(getPackageName(), 0).versionCode) {

                        new UpdateDialog.Builder(this)
                                .setVersionName("v 2.0") // 版本名
                                .setFileSize("10 M") // 文件大小
                                .setForceUpdate(false) // 是否强制更新
                                .setUpdateLog("到底更新了啥") // 更新日志
                                .setDownloadUrl("https://raw.githubusercontent.com/getActivity/AndroidProject/master/AndroidProject.apk") // 下载 url
                                .show();
                    }
                } catch (PackageManager.NameNotFoundException ignored) {}
                break;
            case R.id.btn_dialog_custom: // 自定义对话框
                new BaseDialogFragment.Builder(this)
                        .setContentView(R.layout.dialog_custom)
                        .setAnimStyle(BaseDialog.AnimStyle.SCALE)
                        //.setText(id, "我是预设置的文本")
                        .setOnClickListener(R.id.btn_dialog_custom_ok, new BaseDialog.OnClickListener<ImageView>() {

                            @Override
                            public void onClick(BaseDialog dialog, ImageView view) {
                                dialog.dismiss();
                            }
                        })
                        .addOnShowListener(new BaseDialog.OnShowListener() {
                            @Override
                            public void onShow(BaseDialog dialog) {
                                toast("Dialog  显示了");
                            }
                        })
                        .addOnCancelListener(new BaseDialog.OnCancelListener() {
                            @Override
                            public void onCancel(BaseDialog dialog) {
                                toast("Dialog 取消了");
                            }
                        })
                        .addOnDismissListener(new BaseDialog.OnDismissListener() {
                            @Override
                            public void onDismiss(BaseDialog dialog) {
                                toast("Dialog 销毁了");
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UmengClient.onActivityResult(this, requestCode, resultCode, data);
    }
}