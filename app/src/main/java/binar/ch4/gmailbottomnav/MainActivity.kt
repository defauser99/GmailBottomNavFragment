package binar.ch4.gmailbottomnav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import binar.ch4.gmailbottomnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, MailFragment())
            setReorderingAllowed(true)
            commit()
        }

        binding?.let{
            it.bottomNavBar.setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.mail_menu -> {
                        navigationFragment(MailFragment())
                    }
                    R.id.meet_menu -> {
                        navigationFragment(MeetFragment())
                    }
                }
                return@setOnNavigationItemSelectedListener true
            }

            val badge = it.bottomNavBar.getOrCreateBadge(R.id.mail_menu)
            badge.isVisible = true
            badge.number = 1
        }
    }

    private fun navigationFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
            commit()
        }
    }
}