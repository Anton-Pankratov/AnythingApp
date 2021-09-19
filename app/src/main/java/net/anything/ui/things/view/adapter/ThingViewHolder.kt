package net.anything.ui.things.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.anything.anythingapp.R
import net.anything.anythingapp.databinding.ItemThingBinding
import net.anything.domain.entity.ShowSign
import net.anything.domain.entity.ShowThingEntity

class ThingViewHolder(
    private val binding: ItemThingBinding
) : RecyclerView.ViewHolder(binding.root) {

    var thing: ShowThingEntity? = null
        private set

    fun onBind(
        thing: ShowThingEntity?,
        listener: OnThingClickListener?
    ) {
        this.thing = thing

        binding.apply {
            thing?.apply {
                title.text = id?.formTitle()
                if (checkEmptyThing()) {
                    setThing(listener)
                } else {
                    setEmptyThing()
                }
            }
        }
    }

    private fun ShowThingEntity.setThing(
        listener: OnThingClickListener?
    ) {
        binding.apply {
            sign1Field.formSignText(sign1)
            sign2Field.formSignText(sign2)
            sign3Field.formSignText(sign3)
            root.setOnClickListener(null)
            root.setOnClickListener {
                listener?.onClick(thing)
            }
        }
    }

    private fun setEmptyThing() {
        binding.apply {
            sign1Field.apply {
                setTextColor(Color.RED)
                text = resources.getString(
                    R.string.item_empty_thing
                )
            }
            root.apply {
                removeView(sign2Field)
                removeView(sign3Field)
            }
        }
    }

    private fun Int.formTitle() = "Thing #$this"

    private fun TextView.formSignText(sign: ShowSign?) {
        sign?.apply {
            when {
                !header.isNullOrEmpty() || !value.isNullOrEmpty() -> {
                    val signText = "$header : $value"
                    text = signText
                    setTextColor(Color.DKGRAY)
                }
                header.isNullOrEmpty() && value.isNullOrEmpty() -> {
                    binding.root.removeView(this@formSignText)
                }
            }
        }
    }

    /**
     * @return if 'true' - all fields is empty ('empty entity')
     */
    private fun ShowThingEntity.checkEmptyThing(): Boolean {
        val emptyCheck = mutableListOf<Boolean>()
        getSigns().forEach { sign ->
            emptyCheck.add(
                sign?.header.isNullOrEmpty()
                        && sign?.value.isNullOrEmpty()
            )
        }
        return emptyCheck.toList().contains(false)
    }

    companion object {
        fun create(parent: ViewGroup) = ItemThingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let(::ThingViewHolder)
    }
}