package dev.codewithrivaldo.mysubmission.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.codewithrivaldo.mysubmission.databinding.FragmentDetailUserBinding
import dev.codewithrivaldo.mysubmission.model.data.User
import dev.codewithrivaldo.mysubmission.view.activity.ContainerActivity

class DetailUserFragment : Fragment() {

    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val user = arguments?.getParcelable<User>(EXTRA_USER)

            binding.apply {
                if (user != null) {
                    imgAvatar.setImageResource(user.avatar)
                    tvUsername.text = user.username
                    tvName.text = user.name
                    tvCompany.text = user.company
                    tvLocation.text = user.location
                    tvRepository.text = user.repository + "\nRepository"
                    tvFollower.text = user.follower + "\nFollower"
                    tvFollowing.text = user.following + "\nFollower"
                }
            }
        }

        back()
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
}