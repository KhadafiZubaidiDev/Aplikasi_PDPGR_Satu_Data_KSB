package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_login.*
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity


class LoginActivity : BaseActivity(){

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.title = "Login"
        webViewSetup()
    }

    @SuppressLint("SetJavaScriptEnabled", "NewApi")
    private fun webViewSetup(){
        webViewLogin.webViewClient = WebViewClient()
        webViewLogin.apply {
            loadUrl("https://khadafizubaidi.xyz/login_agr_peliuk")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

}
