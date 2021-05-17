import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eticaretapp.R
import com.example.eticaretapp.model.Product
import kotlinx.android.synthetic.main.recyler_row.view.*

class ProductRecyclerAdapter(val productList : List<Product>, private val listener: Listener) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder>() {
    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyler_row,parent,false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val productImage = holder.itemView.findViewById<ImageView>(R.id.productsImage)
        val productNameText = holder.itemView.findViewById<TextView>(R.id.productsName)
        val productPriceText = holder.itemView.findViewById<TextView>(R.id.productsPrice)

        holder.itemView.addBasketButton.setOnClickListener {
            Toast.makeText(it.context,"Sepete Eklendi ${productList.get(position).name}",Toast.LENGTH_LONG).show()
            listener.onItemClick(productList.get(position))
        }

        productNameText.text = productList.get(position).name
        productPriceText.text = "${productList.get(position).price} TL"
        Glide.with(holder.itemView.context).load(productList.get(position).url).into(productImage)

    }

    override fun getItemCount(): Int {
        return productList.size
    }
    interface Listener {
        fun onItemClick(product: Product)
    }

}