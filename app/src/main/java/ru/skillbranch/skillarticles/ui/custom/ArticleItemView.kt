package ru.skillbranch.skillarticles.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.extensions.LayoutContainer
import ru.skillbranch.skillarticles.R
import ru.skillbranch.skillarticles.data.ArticleItemData
import ru.skillbranch.skillarticles.extensions.attrValue
import ru.skillbranch.skillarticles.extensions.dpToIntPx
import ru.skillbranch.skillarticles.extensions.format
import kotlin.math.max

class ArticleItemView(context: Context) : ViewGroup(context, null, 0), LayoutContainer {
    override val containerView: View = this

    val posterSize = context.dpToIntPx(64)
    val cornerRadius = context.dpToIntPx(8)
    val categorySize = context.dpToIntPx(40)
    val iconSize = context.dpToIntPx(16)
    val defaultMargin = context.dpToIntPx(8)
    val defaultPadding = context.dpToIntPx(16)
    val titleMarginEnd = context.dpToIntPx(24)

    var tv_date: TextView
    var tv_author: TextView
    var tv_title: TextView
    val iv_poster: ImageView
    val iv_category: ImageView
    var tv_description: TextView
    val iv_likes: ImageView
    var tv_likes_count: TextView
    val iv_comments: ImageView
    var tv_comments_count: TextView
    var tv_read_duration: TextView
    val iv_bookmark: ImageView

    private val color = context.getColor(R.color.color_gray)
    private val colorPrimary = context.attrValue(R.attr.colorPrimary)

    init {
        setPadding(defaultPadding)

        tv_date = TextView(context).apply {
            id = R.id.tv_date
            setTextColor(color)
            textSize = 12f
        }
        addView(tv_date)

        tv_author = TextView(context).apply {
            id = R.id.tv_author
            setTextColor(colorPrimary)
            textSize = 12f
        }
        addView(tv_author)

        tv_title = TextView(context).apply {
            id = R.id.tv_title
            setTextColor(colorPrimary)
            textSize = 18f
            setTypeface(typeface, Typeface.BOLD)
        }
        addView(tv_title)

        iv_poster = ImageView(context).apply {
            id = R.id.iv_poster
            layoutParams = LayoutParams(posterSize, posterSize)
        }
        addView(iv_poster)

        iv_category = ImageView(context).apply {
            id = R.id.iv_category
            layoutParams = LayoutParams(categorySize, categorySize)
        }
        addView(iv_category)

        tv_description = TextView(context).apply {
            id = R.id.tv_description
            setTextColor(color)
            textSize = 14f
        }
        addView(tv_description)

        iv_likes = ImageView(context).apply {
            id = R.id.iv_likes
            layoutParams = LayoutParams(iconSize, iconSize)
            setImageResource(R.drawable.ic_favorite_black_24dp)
            imageTintList = ColorStateList.valueOf(color)
        }
        addView(iv_likes)

        tv_likes_count = TextView(context).apply {
            id = R.id.tv_likes_count
            setTextColor(color)
            textSize = 12f
        }
        addView(tv_likes_count)

        iv_comments = ImageView(context).apply {
            id = R.id.iv_comments
            layoutParams = LayoutParams(iconSize, iconSize)
            setImageResource(R.drawable.ic_insert_comment_black_24dp)
            imageTintList = ColorStateList.valueOf(color)
        }
        addView(iv_comments)

        tv_comments_count = TextView(context).apply {
            id = R.id.tv_comments_count
            setTextColor(color)
            textSize = 12f
        }
        addView(tv_comments_count)

        tv_read_duration = TextView(context).apply {
            id = R.id.tv_read_duration
            setTextColor(color)
            textSize = 12f
        }
        addView(tv_read_duration)

        iv_bookmark = ImageView(context).apply {
            id = R.id.iv_bookmark
            layoutParams = LayoutParams(iconSize, iconSize)
            setImageResource(R.drawable.bookmark_states)
            imageTintList = ColorStateList.valueOf(color)
        }
        addView(iv_bookmark)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var usedHeight = paddingTop
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)

        measureChild(tv_date, widthMeasureSpec, heightMeasureSpec)
        tv_author.maxWidth =
            width - paddingLeft - paddingRight - (tv_date.measuredWidth + 2 * defaultMargin)
        measureChild(tv_author, widthMeasureSpec, heightMeasureSpec)

        tv_title.maxWidth = width - paddingLeft - paddingRight - posterSize - titleMarginEnd
        measureChild(tv_title, widthMeasureSpec, heightMeasureSpec)
        measureChild(iv_poster, widthMeasureSpec, heightMeasureSpec)
        measureChild(iv_category, widthMeasureSpec, heightMeasureSpec)

        measureChild(tv_description, widthMeasureSpec, heightMeasureSpec)

        measureChild(iv_likes, widthMeasureSpec, heightMeasureSpec)
        measureChild(tv_likes_count, widthMeasureSpec, heightMeasureSpec)
        measureChild(iv_comments, widthMeasureSpec, heightMeasureSpec)
        measureChild(tv_comments_count, widthMeasureSpec, heightMeasureSpec)
        measureChild(tv_read_duration, widthMeasureSpec, heightMeasureSpec)
        measureChild(iv_bookmark, widthMeasureSpec, heightMeasureSpec)

        usedHeight += tv_date.measuredHeight
        usedHeight += defaultMargin + max(tv_title.measuredHeight, posterSize + categorySize / 2)
        usedHeight += defaultMargin + tv_description.measuredHeight
        usedHeight += defaultMargin +
                listOf(
                    iconSize,
                    tv_likes_count.measuredHeight,
                    tv_comments_count.measuredHeight,
                    tv_read_duration.measuredHeight
                ).max()!! + paddingBottom

        setMeasuredDimension(width, usedHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var usedHeight = paddingTop
        val bodyWidth = right - left - paddingLeft - paddingRight
        var left = paddingLeft
        val right = left + bodyWidth

        tv_date.layout(
            left,
            usedHeight,
            left + tv_date.measuredWidth,
            usedHeight + tv_date.measuredHeight
        )

        tv_author.layout(
            tv_date.right + 2 * defaultMargin,
            usedHeight,
            right,
            usedHeight + tv_author.measuredHeight
        )

        usedHeight += tv_date.measuredHeight + defaultMargin

        val headerIconsHeight = iv_poster.measuredHeight + iv_category.measuredHeight / 2
        val headerHeight = max(tv_title.measuredHeight, headerIconsHeight)
        val tvTitleSpace =
            if (headerHeight > tv_title.measuredHeight) (headerHeight - tv_title.measuredHeight) / 2
            else 0
        val headerIconsSpace =
            if (headerHeight > headerIconsHeight) (headerHeight - headerIconsHeight) / 2
            else 0

        tv_title.layout(
            left,
            usedHeight + tvTitleSpace,
            right - iv_poster.measuredWidth - titleMarginEnd,
            usedHeight + tv_title.measuredHeight + tvTitleSpace
        )

        iv_poster.layout(
            right - iv_poster.measuredWidth,
            usedHeight + headerIconsSpace,
            right,
            usedHeight + iv_poster.measuredHeight + headerIconsSpace
        )

        iv_category.layout(
            iv_poster.left - iv_category.measuredWidth / 2,
            iv_poster.bottom - iv_category.measuredHeight / 2,
            iv_poster.left + iv_category.measuredWidth / 2,
            iv_poster.bottom + iv_category.measuredHeight / 2
        )

        usedHeight += headerHeight + defaultMargin

        tv_description.layout(
            left,
            usedHeight,
            right,
            usedHeight + tv_description.measuredHeight
        )

        usedHeight += tv_description.measuredHeight + defaultMargin

        var iconTextSpace = (tv_likes_count.measuredHeight - iv_likes.measuredHeight) / 2

        iv_likes.layout(
            left,
            usedHeight + iconTextSpace,
            left + iconSize,
            usedHeight + iconSize + iconTextSpace
        )

        left = iv_likes.right + defaultMargin

        tv_likes_count.layout(
            left,
            usedHeight,
            left + tv_likes_count.measuredWidth,
            usedHeight + tv_likes_count.measuredHeight
        )

        left = tv_likes_count.right + 2 * defaultMargin
        iconTextSpace = (tv_comments_count.measuredHeight - iv_comments.measuredHeight) / 2

        iv_comments.layout(
            left,
            usedHeight + iconTextSpace,
            left + iconSize,
            usedHeight + iconSize + iconTextSpace
        )

        left = iv_comments.right + defaultMargin

        tv_comments_count.layout(
            left,
            usedHeight,
            left + tv_comments_count.measuredWidth,
            usedHeight + tv_comments_count.measuredHeight
        )

        left = tv_comments_count.right + defaultPadding

        tv_read_duration.layout(
            left,
            usedHeight,
            left + tv_read_duration.measuredWidth,
            usedHeight + tv_read_duration.measuredHeight
        )

        left = defaultPadding
        iconTextSpace = (tv_read_duration.measuredHeight - iv_bookmark.measuredHeight) / 2

        iv_bookmark.layout(
            left + bodyWidth - iconSize,
            usedHeight + iconTextSpace,
            left + bodyWidth,
            usedHeight + iconSize + iconTextSpace
        )
    }

    fun bind(item: ArticleItemData) {
        tv_date.text = item.date.format()
        tv_author.text = item.author
        tv_title.text = item.title

        Glide.with(context)
            .load(item.poster)
            .transform(CenterCrop(), RoundedCorners(cornerRadius))
            .override(posterSize)
            .into(iv_poster)

        Glide.with(context)
            .load(item.categoryIcon)
            .transform(CenterCrop(), RoundedCorners(cornerRadius))
            .override(categorySize)
            .into(iv_category)

        tv_description.text = item.description
        tv_likes_count.text = "${item.likeCount}"
        tv_comments_count.text = "${item.commentCount}"
        tv_read_duration.text = "${item.readDuration} min read"
    }
}