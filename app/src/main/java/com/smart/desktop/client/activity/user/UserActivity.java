package com.smart.desktop.client.activity.user;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.common.enums.UserType;
import com.smart.desktop.common.widget.RoundImageView;
import com.smart.desktop.common.widget.TitleBuilder;
import com.smart.desktop.core.api.ApiRepository;
import com.smart.desktop.core.bean.UserInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 操作员信息
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年12月21日
 */
public class UserActivity extends BaseActivity implements UserContract.View {

    @BindView(R.id.iv_avatar)
    RoundImageView ivAvatar;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.title_tv_right)
    TextView titleTvRight;
    @BindView(R.id.lv_user_list)
    ListView lvUserList;

    private UserListAdapter mAdapter;
    private List<UserInfo> mUserList;

    private UserContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new UserPresenter(this, ApiRepository.getInstance());
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initView() {
        //标题栏
        new TitleBuilder(this)
                .setLeftImage(R.drawable.arrow_icon)
                .setTitleText(getString(R.string.user_info))
                .setRightText(getString(R.string.add))
                .build();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        titleTvRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onStart();
    }

    @OnClick({R.id.title_iv_left, R.id.title_tv_right})
    public void onClick(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_iv_left:
                finish();
                break;
            case R.id.title_tv_right:
                mPresenter.addUser();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCurrentUser(String userNo) {
        UserType type = UserType.getUserType(userNo);
        switch (type){
            case SUPERVISOR:
                titleTvRight.setVisibility(View.VISIBLE);
                tvDesc.setText("主管操作员(" + userNo + ")");
                break;
            case ADMIN:
                tvDesc.setText("系统管理员(" + userNo + ")");
                break;
            case OTHER:
            default:
                tvDesc.setText("普通操作员(" + userNo + ")");
                break;
        }
    }

    @Override
    public void onUserList(List<UserInfo> userList) {
        if (userList == null || userList.size() == 0) return;
        if (mAdapter == null) {
            mAdapter = new UserListAdapter(mContext);
            lvUserList.setAdapter(mAdapter);
        }
        this.mUserList = userList;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 操作员管理适配器
     */
    class UserListAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public UserListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (mUserList != null) return mUserList.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mUserList != null) return mUserList.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_user_list, null);
                mHolder = new ViewHolder(convertView);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }

            UserInfo info = mUserList.get(position);
            mHolder.tvId.setText(String.valueOf((position + 1)));
            mHolder.tvUserNo.setText(info.getUserNo());
            mHolder.tvEdit.setText(R.string.edit);
            mHolder.tvDelete.setText(R.string.delete);

            //编辑事件
//            mHolder.tvEdit.setOnClickListener(v -> mPresenter.editOperatorInfo(info.getOperatorNo()));

            //删除事件
//            mHolder.tvDelete.setOnClickListener(v -> mPresenter.confirmDelete(info.getOperatorNo()));

            return convertView;
        }

        class ViewHolder {

            @BindView(R.id.tv_id)
            TextView tvId;
            @BindView(R.id.tv_operator_no)
            TextView tvUserNo;
            @BindView(R.id.tv_edit)
            TextView tvEdit;
            @BindView(R.id.tv_delete)
            TextView tvDelete;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
