package com.smart.desktop.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.desktop.R;
import com.smart.desktop.common.constant.SysConstant;
import com.smart.desktop.common.enums.OfflineState;
import com.smart.desktop.common.enums.TransType;
import com.smart.desktop.common.enums.UploadState;
import com.smart.desktop.common.utils.StringUtils;
import com.smart.desktop.common.utils.TransUtils;
import com.smart.desktop.core.bean.TransRecord;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 交易列表适配器
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年08月14日
 */
public class TransListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = TransListAdapter.class.getSimpleName();
    private static final int TYPE_TRANS_ITEM = 0;
    private static final int TYPE_LOAD_FOOTER = 1;

    private Context mContext;
    private LayoutInflater mInflater;
    private List<TransRecord> mTransList;
    private OnItemClickListener mListener;

    public TransListAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * 添加交易列表
     *
     * @param transList
     */
    public void addTransList(List<TransRecord> transList) {
        mTransList = transList;
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        if (mTransList != null && !mTransList.isEmpty()) {
            mTransList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mTransList.size() <= 4) {
            return mTransList.size();
        }
        return mTransList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        //4为临界值
        if (position + 1 == getItemCount() && getItemCount() > 4) {
            return TYPE_LOAD_FOOTER;
        } else {
            return TYPE_TRANS_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TRANS_ITEM) {
            View view = mInflater.inflate(R.layout.item_trans_list, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_LOAD_FOOTER) {
            View view = mInflater.inflate(R.layout.item_loading_layout, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == mTransList.size()) {
            return;
        }
        if (holder instanceof ItemViewHolder) {
            TransRecord transRecord = mTransList.get(position);
            ItemViewHolder mHolder = (ItemViewHolder) holder;
            //先初始化为不可见，防止显示时数据混乱
            mHolder.llCardNo.setVisibility(View.GONE);
            mHolder.tvTag.setVisibility(View.GONE);
            //交易类型
            mHolder.tvTradeType.setText(TransType.values()[transRecord.getTransType()].getName());
            //交易金额
            mHolder.tvTradeAmount.setText(StringUtils.formatAmount(transRecord.getAmount(), SysConstant.FUND_DIGITS));
            //凭证号
            mHolder.tvVoucherNo.setText(transRecord.getTraceNum());
            switch (TransType.values()[transRecord.getTransType()]) {
                case SCAN_SALE:
                case SCAN_VOID:
                case SCAN_REFUND:

                    break;
                default:
                    //卡号
                    if (!TextUtils.isEmpty(transRecord.getPan())) {
                        mHolder.llCardNo.setVisibility(View.VISIBLE);
                        mHolder.tvCardNo.setText(TransUtils.maskPan(transRecord.getPan()));
                    }
                    break;
            }
            //交易时间
            mHolder.tvTradeTime.setText(StringUtils.formatDateTime(transRecord.getDate(), transRecord.getTime()));
            //标记
            if (transRecord.getIsVoid()) {
                mHolder.tvTag.setVisibility(View.VISIBLE);
                mHolder.tvTag.setText(R.string.recalled);
            } else if (OfflineState.APPROVE.equals(OfflineState.values()[transRecord.getOfflineState()])) {
                mHolder.tvTag.setVisibility(View.VISIBLE);
                //脱机交易
                UploadState uploadState = UploadState.values()[transRecord.getUploadState()];
                switch (uploadState) {
                    case NONE:
                        mHolder.tvTag.setText(R.string.un_upload);
                        break;
                    case OFFLINE_UPLOAD_FAILED:
                        mHolder.tvTag.setText(R.string.upload_fail);
                        break;
                    case OFFLINE_UPLOAD_REJECTED:
                        mHolder.tvTag.setText(R.string.upload_rejected);
                        break;
                    case OFFLINE_UPLOAD_SUCCESSFUL:
                        mHolder.tvTag.setText(R.string.is_upload);
                        break;
                    default:
                        break;
                }
            }
        }
        holder.itemView.setTag(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_trade_type)
        TextView tvTradeType;
        @BindView(R.id.tv_trade_amount)
        TextView tvTradeAmount;
        @BindView(R.id.tv_voucher_no)
        TextView tvVoucherNo;
        @BindView(R.id.ll_card_no)
        LinearLayout llCardNo;
        @BindView(R.id.tv_card_no)
        TextView tvCardNo;
        @BindView(R.id.tv_trade_time)
        TextView tvTradeTime;
        @BindView(R.id.tv_tag)
        TextView tvTag;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick((Integer) view.getTag());
            }
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
