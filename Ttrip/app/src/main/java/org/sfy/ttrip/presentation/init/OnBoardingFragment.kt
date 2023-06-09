package org.sfy.ttrip.presentation.init

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import org.sfy.ttrip.MainActivity
import org.sfy.ttrip.R
import org.sfy.ttrip.databinding.FragmentOnboardingBinding
import org.sfy.ttrip.presentation.base.BaseFragment

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {

    private val authViewModel by viewModels<AuthViewModel>()

    override fun initView() {
        initBanner()

        observeData()
        authViewModel.requestAccessToken()
    }

    private fun initBanner() {
        binding.apply {
            vpBanner.adapter = OnBoardingAdapter(this@OnBoardingFragment)
            ciBanner.setViewPager(vpBanner)

            vpBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    vpBanner.isUserInputEnabled = position != 2
                }
            })

            tvSkipOnboarding.setOnClickListener {
                vpBanner.currentItem = 2
            }

            ivOnboardingLoader.setOnClickListener {
                vpBanner.currentItem = 2
            }
        }
    }

    private fun observeData() {
        authViewModel.isAutoLogin.observe(viewLifecycleOwner) {
            if (it) {
                authViewModel.emptyNickname.observe(viewLifecycleOwner) {
                    if (authViewModel.isFreeze.value == false) {
                        if (it == true) {
                            showToast("로그인되었습니다.")
                            navigate(OnBoardingFragmentDirections.actionOnboardingFragmentToSignUpInformationFragment())
                        } else if (it == false) {
                            showToast("돌아오신걸 환영해요!")
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    } else {
                        // 관리자 메일 추가 필요
                        showToast("자동로그인에 실패햇습니다.\n이메일을 통해 \n소명서를 제출해주세요")
                    }
                }
            }
        }
    }

    fun eraseIndicator() {
        binding.apply {
            ciBanner.visibility = View.GONE
            tvSkipOnboarding.visibility = View.GONE
            ivOnboardingLoader.visibility = View.GONE
        }
    }
}