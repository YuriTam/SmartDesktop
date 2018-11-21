package com.smart.desktop.client.activity.trans_list;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.common.utils.UIUtils;
import com.smart.desktop.R;
import com.smart.desktop.base.BaseActivity;
import com.smart.desktop.client.adapter.TransListAdapter;
import com.smart.desktop.common.widget.RecyclerDivider;
import com.smart.desktop.common.widget.TitleBuilder;
import com.smart.desktop.core.api.ApiRepository;
import com.smart.desktop.core.bean.TransRecord;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 交易明细
 *
 * @author 谭忠扬-YuriTam
 * @time 2018年11月21日
 */
public class TransListActivity extends BaseActivity implements TransListContract.View, TransListAdapter.OnItemClickListener {

    @BindView(R.id.et_voucher_no)
    EditText etVoucherNo;
    @BindView(R.id.rv_trans_list)
    RecyclerView rvTransList;

    private boolean isLoading;
    private LinearLayoutManager mLayoutManager;
    private TransListAdapter mListAdapter;
    private List<TransRecord> mTransList;
    private TransListContract.Presenter mPresenter;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new TransListPresenter(this, ApiRepository.getInstance());
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_trans_list;
    }

    @Override
    protected void initView() {
        //初始化标题栏
        new TitleBuilder(this)
                .setExternalTitleBgColor(getResources().getColor(R.color.holo_blue_light))
                .setLeftImage(R.drawable.arrow_icon)
                .setRightText(getString(R.string.trans_list_print_detail))
                .setTitleText(getString(R.string.trans_list))
                .build();
    }

    @Override
    protected void initEvent() {
        //监听滑动事件
        rvTransList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mListAdapter.getItemCount();
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount == (lastVisibleItemPosition + 1) && totalItemCount > 4) {
                    //这里加载更多数据
                    mHandler.postDelayed(() -> mPresenter.getMoreTransList(), 1000);
                    isLoading = true;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mLayoutManager = new LinearLayoutManager(mContext);
        rvTransList.setLayoutManager(mLayoutManager);
        rvTransList.addItemDecoration(new RecyclerDivider(mContext, 15, RecyclerDivider.HORIZONTAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //首次加载数据
        mPresenter.onStart();
    }

    @OnClick({R.id.title_iv_left, R.id.iv_search, R.id.title_tv_right})
    public void onClick(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()) {
            case R.id.title_iv_left:
                finish();
                break;
            case R.id.iv_search:
                //查询
                mPresenter.getTransRecord(etVoucherNo.getText().toString());
                break;
            case R.id.title_tv_right:
                showToast("敬请期待");
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        if (mTransList == null || position == mTransList.size()) {
            return;  //防止点击上下页时闪退
        }
        //跳转到订单详情页面
        TransRecord transRecord = mTransList.get(position);
//        startActivityForResult(TransDetailActivity.newInstance(mContext, transRecord.getTraceNum()), REQ_CODE);
        //startActivity(TransDetailActivity.newInstance(mContext, transRecord.getTraceNum()));
    }

    @Override
    public void onTransList(List<TransRecord> transList, boolean isSearch) {
        if (mTransList == null) {
            mTransList = new ArrayList<>();
        }
        //搜索时返回一个交易记录，先清除之前的交易记录
        if (transList.size() == 1 && mTransList.size() != 0 && isSearch) {
            mTransList.clear();
        }
        mTransList.addAll(transList);
        if (mListAdapter == null) {
            mListAdapter = new TransListAdapter(mContext);
            rvTransList.setAdapter(mListAdapter);
            mListAdapter.setOnItemClickListener(this);
        }
        mListAdapter.addTransList(mTransList);
        //移除上拉加载更多视图
        mListAdapter.notifyItemRemoved(mListAdapter.getItemCount());
        isLoading = false;
    }

    @Override
    public void showClearList() {
        if (mTransList != null && !mTransList.isEmpty()){
            mTransList.clear();
        }
        if (mListAdapter != null){
            mListAdapter.clear();
        }
        rvTransList.removeAllViews();
    }

    @Override
    public void showNotMoreData() {
        showToast(getString(R.string.has_no_more_data));
        //移除上拉加载更多视图
        mListAdapter.notifyItemRemoved(mListAdapter.getItemCount());
        isLoading = false;
    }

    @Override
    public void showEmptyList() {
        finish();
    }

    @Override
    public void showMsg(String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void setPresenter(TransListContract.Presenter presenter) {
        mPresenter = presenter;
    }

}
