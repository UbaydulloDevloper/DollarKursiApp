package adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.developer.dollarkursiapp.R
import com.developer.dollarkursiapp.databinding.ActivityMainBinding.inflate
import com.developer.dollarkursiapp.databinding.ItemDollarViewBinding
import kotlinx.android.synthetic.main.item_custom_dialog.view.*
import models.DollarClass
import java.util.*

class MyAdapter(private val list: List<DollarClass>) :
    RecyclerView.Adapter<MyAdapter.Vh>() {


    inner class Vh(var itemRv: ItemDollarViewBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: DollarClass) {
            val volute = user.Rate.toDouble()
            itemRv.nameVolute.text = user.CcyNm_UZ
            itemRv.voluteText.text = user.Ccy
            itemRv.countUzb.text = user.Rate
            itemRv.image.text = getFlag(user.Ccy)
            itemRv.voluteCount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (s.isNotEmpty()) {
                        val count1 = itemRv.voluteCount.text.toString().toInt()
                        val totle = String.format("%.2f", count1 * volute)
                        itemRv.countUzb.text = totle
                    } else {
                        itemRv.countUzb.text = user.Rate
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
            itemRv.root.setOnClickListener {

                val alertDialog = AlertDialog.Builder(itemRv.root.context)
                val dialog = alertDialog.create()

                val dialogView = LayoutInflater.from(itemRv.root.context)
                    .inflate(R.layout.item_custom_dialog, null, false)
                dialogView.Ccy.text = user.Ccy
                dialogView.CcyNm_EN_name.text = user.CcyNm_EN
                dialogView.CcyNm_RU_count.text = user.CcyNm_RU
                dialogView.CcyNm_UZ_count.text = user.CcyNm_UZ
                dialogView.Rate.text = user.Rate
                dialogView.CcyNm_RU_count.text = user.CcyNm_RU




                dialog.setView(dialogView)
                dialog.show()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemDollarViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    fun getFlag(countryCode: String): String {
        return countryCode
            .toUpperCase(Locale.US).map { char ->
                Character.codePointAt("$char", 0) - 0x41 + 0x1F1E6
            }
            .map { codePoint ->
                Character.toChars(codePoint)
            }
            .joinToString(separator = "") { charArray ->
                String(charArray)
            }
    }

    override fun getItemCount(): Int = list.size
}
