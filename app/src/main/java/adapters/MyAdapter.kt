package adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.dollarkursiapp.databinding.ItemDollarViewBinding
import models.DollarClass

class MyAdapter(private val list: List<DollarClass>) :
    RecyclerView.Adapter<MyAdapter.Vh>() {


    inner class Vh(var itemRv: ItemDollarViewBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: DollarClass ) {
            val volute = user.Rate.toDouble()
            itemRv.nameVolute.text = user.CcyNm_UZ
            itemRv.voluteText.text = user.Ccy
            itemRv.countUzb.text = user.Rate
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
                        val totle = String.format("%.2f",count1 * volute)
                        itemRv.countUzb.text = totle
                    } else {
                        itemRv.countUzb.text = user.Rate
                    }

                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemDollarViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
