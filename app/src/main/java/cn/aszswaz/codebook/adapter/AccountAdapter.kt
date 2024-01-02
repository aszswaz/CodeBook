package cn.aszswaz.codebook.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import cn.aszswaz.codebook.MainActivity
import cn.aszswaz.codebook.R
import cn.aszswaz.codebook.dialog.AccountDialog
import cn.aszswaz.codebook.entity.Account

@Suppress("MemberVisibilityCanBePrivate")
class AccountAdapter(
    val context: MainActivity
) : BaseAdapter() {
    private val inflater = LayoutInflater.from(context)

    var list: List<Account> = emptyList()
        set(newList) {
            field = newList
            notifyDataSetChanged()
        }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Account {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val account = list[position]
        val view = inflater.inflate(R.layout.list_element, parent, false)

        val platform = view.findViewById<TextView>(R.id.account_platform)
        val detail = view.findViewById<TextView>(R.id.account_detail)

        platform.text = account.platform
        detail.text = "用户名：${account.account}，密码：${account.password}"

        // 打开账号的详细信息
        view.setOnLongClickListener {
            val dialog = AccountDialog(context, account)
            dialog.show(context.supportFragmentManager, account.platform)
            false
        }

        // 将密码复制到剪切板
        view.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("account password", account.password)
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "密码已复制到剪切板", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}