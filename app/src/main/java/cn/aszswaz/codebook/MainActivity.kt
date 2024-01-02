package cn.aszswaz.codebook

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import cn.aszswaz.codebook.adapter.AccountAdapter
import cn.aszswaz.codebook.dialog.AccountDialog
import cn.aszswaz.codebook.helper.AccountHelper

class MainActivity : AppCompatActivity(), OnClickListener {
    lateinit var adapter: AccountAdapter
        private set
    lateinit var helper: AccountHelper
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = AccountAdapter(this)
        helper = AccountHelper(this)
        adapter.list = helper.findAll()
        findViewById<ListView>(R.id.account_list).adapter = adapter
        findViewById<Button>(R.id.account_create_btn)
            .setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // 打开创建账户对话框
        val dialog = AccountDialog(this)
        dialog.show(supportFragmentManager, "create account")
    }
}