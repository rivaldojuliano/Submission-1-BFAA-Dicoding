package dev.codewithrivaldo.mysubmission.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.codewithrivaldo.mysubmission.R
import dev.codewithrivaldo.mysubmission.databinding.FragmentDetailUserBinding
import dev.codewithrivaldo.mysubmission.model.data.User
import dev.codewithrivaldo.mysubmission.view.activity.ContainerActivity

class DetailUserFragment : Fragment() {

    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataUser()
        back()
    }

    private fun getDataUser() {
        if (arguments != null) {
            val user = arguments?.getParcelable<User>(EXTRA_USER)

            binding.apply {
                if (user != null) {
                    imgAvatar.setImageResource(user.avatar)
                    tvUsername.text = user.username
                    tvName.text = user.name
                    tvCompany.text = user.company
                    tvLocation.text = user.location
                    tvRepository.text = getString(R.string.repository, user.repository)
                    tvFollower.text = getString(R.string.repository, user.follower)
                    tvFollowing.text = getString(R.string.following, user.following)
                }
            }
        }
    }

    private fun back() {
        binding.btnBack.setOnClickListener {
            val mIntent = Intent(context, ContainerActivity::class.java)
            startActivity(mIntent)
            activity?.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}