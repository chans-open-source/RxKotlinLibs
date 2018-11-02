package com.chansos.libs.rxkotlin.classes

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chansos.libs.rxkotlin.Kt
import com.chansos.libs.rxkotlin.utils.ObjectUtils

@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class BaseRecyclerViewHolder internal constructor(itemView: View, private val context: Context) :
    RecyclerView.ViewHolder(itemView) {
    private val imageViewList: HashSet<Int> by lazy {
        HashSet<Int>()
    }

    companion object {
        internal fun create(itemView: View, parent: ViewGroup): BaseRecyclerViewHolder {
            return BaseRecyclerViewHolder(itemView, parent.context)
        }

        fun createView(parent: ViewGroup, rootLayoutResId: Int): View {
            return LayoutInflater.from(parent.context).inflate(rootLayoutResId, parent, false)
        }
    }

    /**
     * 从子RecyclerView的子View中获取View实例
     * */
    fun <T : View?> get(viewId: Int): T {
        return Kt.UI.get<T>(itemView, viewId)
    }

    /**
     * 加载图片
     * */
    fun setImage(viewId: Int, image: String) {
        if (context is Activity) {
            Kt.Image.load(get(viewId), image, context)
        } else if (context is Fragment) {
            Kt.Image.load(get(viewId), image, context)
        }
        imageViewList.add(viewId)
    }

    /**
     * 加载图片
     * */
    fun setImage(viewId: Int, image: Int) {
        if (context is Activity) {
            Kt.Image.load(get(viewId), image, context)
        } else if (context is Fragment) {
            Kt.Image.load(get(viewId), image, context)
        }
        imageViewList.add(viewId)
    }

    /**
     * 设置文本
     * */
    fun setText(viewId: Int, content: String) {
        (get(viewId) as TextView).text = content
    }

    /**
     * 设置文本
     * */
    fun setText(viewId: Int, content: Int) {
        (get(viewId) as TextView).setText(content)
    }

    /**
     * 释放RecyclerView的子View中的View
     * */
    fun release() {
        imageViewList.forEach { viewId ->
            run {
                if (context is Activity && !context.isDestroyed) {
                    Kt.Image.release(get(viewId), context)
                } else if (context is Fragment && !context.isDetached) {
                    Kt.Image.release(get(viewId), context)
                }
            }
        }
        ObjectUtils.destory(this)
    }
}