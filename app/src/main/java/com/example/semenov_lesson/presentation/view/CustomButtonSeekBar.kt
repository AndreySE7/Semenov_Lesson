package com.example.semenov_lesson.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toDrawable
import com.example.semenov_lesson.R
import com.example.semenov_lesson.databinding.CustomButtonSeekBarBinding

const val DEFAULT_STRING_ATTR = ""

class CustomButtonSeekBar(
    context: Context,
    attrs: AttributeSet?
) : CardView(context, attrs) {

    private val binding: CustomButtonSeekBarBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(
            R.layout.custom_button_seek_bar,
            this,
            true
        )
        binding = CustomButtonSeekBarBinding.bind(this)
        initializeAndSetAttr(
            attrs
        )
    }

    @SuppressLint("Recycle")
    private fun initializeAndSetAttr(
        attrs: AttributeSet?
    ) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomButtonSeekBar
        )
        val seekBarButtonLogo = typedArray.getResourceId(
            R.styleable.CustomButtonSeekBar_button_logo, 0
        )
        AppCompatResources.getDrawable(context, seekBarButtonLogo)

        val seekBarTitle = typedArray.getString(
            R.styleable.CustomButtonSeekBar_title
        ) ?: DEFAULT_STRING_ATTR

        val seekBarSubtitle = typedArray.getString(
            R.styleable.CustomButtonSeekBar_subtitle
        ) ?: DEFAULT_STRING_ATTR

        setPicture(seekBarButtonLogo.toDrawable())
        setTitle(seekBarTitle)
        setSubtitle(seekBarSubtitle)

    }

    private fun setPicture(seekBarButtonLogo: Drawable?) {
        binding.buttonLogo
            .setImageDrawable(seekBarButtonLogo)
    }

    private fun setTitle(seekBarTitle: String) {
        binding.title
            .text = seekBarTitle
    }

    private fun setSubtitle(seekBarSubtitle: String) {
        binding.subtitle
            .text = seekBarSubtitle
    }

}