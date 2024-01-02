package cn.aszswaz.codebook.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import cn.aszswaz.codebook.MainActivity
import cn.aszswaz.codebook.R
import cn.aszswaz.codebook.entity.Account
import cn.aszswaz.codebook.util.randomPwd

class AccountDialog(
    context: MainActivity,
    private val account: Account? = null
) : OnClickListener, DialogFragment() {
    private val helper = context.helper
    private val adapter = context.adapter

    private lateinit var view: View
    private lateinit var platformView: EditText
    private lateinit var accountView: EditText
    private lateinit var passwordView: EditText
    private lateinit var lengthView: EditText
    private lateinit var numberView: CheckBox
    private lateinit var letterView: CheckBox
    private lateinit var symbolView: CheckBox

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity ?: throw IllegalStateException("Activity cannot be null")

        val builder = AlertDialog.Builder(activity)

        view = View.inflate(activity, R.layout.account_dialog, null)
        platformView = view.findViewById(R.id.account_detail_platform)
        accountView = view.findViewById(R.id.account_detail_account)
        passwordView = view.findViewById(R.id.account_detail_password)
        lengthView = view.findViewById(R.id.account_detail_length)
        numberView = view.findViewById(R.id.account_detail_number)
        letterView = view.findViewById(R.id.account_detail_letter)
        symbolView = view.findViewById(R.id.account_detail_symbol)
        val randomBtn = view.findViewById<Button>(R.id.account_detail_password_random)

        builder.setView(view)
        builder.setPositiveButton("确认", this)
        builder.setNegativeButton("取消") { dialog, _ -> dialog.cancel() }

        account?.let {
            platformView.setText(it.platform)
            accountView.setText(it.account)
            passwordView.setText(it.password)
            lengthView.setText(it.length.toString())
            numberView.isChecked = it.number
            letterView.isChecked = it.letter
            symbolView.isChecked = it.symbol
        }

        // 设置随机密码
        randomBtn.setOnClickListener {
            val length = lengthView.text.toString().toInt()
            val letter = letterView.isChecked
            val number = numberView.isChecked
            val symbol = symbolView.isChecked
            passwordView.setText(randomPwd(length, letter, number, symbol))
        }

        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        dialog?.cancel()

        val account = this.account ?: Account()

        account.platform = platformView.text.toString()
        account.account = accountView.text.toString()
        account.password = passwordView.text.toString()
        account.length = lengthView.text.toString().toInt()
        account.letter = letterView.isChecked
        account.number = numberView.isChecked
        account.symbol = symbolView.isChecked

        if (this.account == null)
            helper.insert(account)
        else
            helper.update(account)

        adapter.list = helper.findAll()
    }
}