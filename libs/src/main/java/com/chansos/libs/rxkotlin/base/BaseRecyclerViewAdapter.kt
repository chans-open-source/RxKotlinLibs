package com.chansos.libs.rxkotlin.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.chansos.libs.rxkotlin.support.ObjectUtils

/**
 * 通用的RecyclerView适配器
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseRecyclerViewHolder>() {
    private val dataList: ArrayList<T> by lazy {
        ArrayList<T>()
    }
    private val viewHolderList: ArrayList<BaseRecyclerViewHolder> by lazy {
        ArrayList<BaseRecyclerViewHolder>()
    }
    var onItemClickListener: OnItemClickListener? = null
    var onItemLongClickListener: OnItemLongClickListener? = null

    /**
     * 点击事件
     * */
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    /**
     * 长按事件
     * */
    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val view = BaseRecyclerViewHolder.createView(parent, getRootLayoutResId())
        onViewCreate(view)
        val viewHolder = BaseRecyclerViewHolder.create(view, parent)
        viewHolderList.add(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: BaseRecyclerViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener { v ->
            if (onItemClickListener != null) {
                onItemClickListener?.onItemClick(v, position)
            }
        }
        viewHolder.itemView.setOnLongClickListener(View.OnLongClickListener { v ->
            if (onItemLongClickListener == null) {
                false
            } else {
                return@OnLongClickListener onItemLongClickListener?.onItemLongClick(v, position)!!
            }
        })
        onBind(viewHolder, dataList[position], position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    /**
     * 替换数据
     * */
    fun setDataList(list: ArrayList<T>) {
        dataList.clear()
        appendDataList(list)
    }

    /**
     * 追加数据
     * */
    fun appendDataList(list: ArrayList<T>) {
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * 释放RecyclerView中的子View
     * */
    fun release() {
        viewHolderList.forEach { viewHolder ->
            run {
                viewHolder.release()
            }
        }
        ObjectUtils.destory(this)
    }

    /**
     * 获取LayoutId
     * */
    abstract fun getRootLayoutResId(): Int

    /**
     * View创建事件
     * */
    abstract fun onViewCreate(view: View)

    /**
     * 数据绑定事件
     * */
    abstract fun onBind(viewHolder: BaseRecyclerViewHolder, data: T, position: Int)
}
