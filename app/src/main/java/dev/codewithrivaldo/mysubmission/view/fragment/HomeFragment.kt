package dev.codewithrivaldo.mysubmission.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.codewithrivaldo.mysubmission.R
import dev.codewithrivaldo.mysubmission.adapter.CardViewUserAdapter
import dev.codewithrivaldo.mysubmission.databinding.FragmentHomeBinding
import dev.codewithrivaldo.mysubmission.model.data.User

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.addAll(getListUser())
        showCardView()
    }

    private fun getListUser(): ArrayList<User> {
        val dataAvatar = resources.obtainTypedArray(R.array.avatar)
        val dataName = resources.getStringArray(R.array.name)
        val dataUsername = resources.getStringArray(R.array.username)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataFollower = resources.getStringArray(R.array.follower)
        val dataFollowing = resources.getStringArray(R.array.following)

        val listUser = ArrayList<User>()
        listUser.clear()

        for (position in dataName.indices) {
            val user = User(
                dataName[position],
                dataUsername[position],
                dataAvatar.getResourceId(position, -1),
                dataCompany[position],
                dataLocation[position],
                dataRepository[position],
                dataFollower[position],
                dataFollowing[position]
            )

            listUser.add(user)
        }
        dataAvatar.recycle()
        return listUser
    }

    private fun showCardView() {
        binding.rvUser.setHasFixedSize(true)

        binding.rvUser.layoutManager = LinearLayoutManager(context)
        val cardViewUserAdapter = CardViewUserAdapter(list)
        binding.rvUser.adapter = cardViewUserAdapter

        cardViewUserAdapter.setOnItemClickCallback(object :
            CardViewUserAdapter.OnItemCLickCallback {
            override fun onItemCLicked(user: User) {
                showSelectedUser(user)
            }
        })
    }

    fun showSelectedUser(user: User) {
        val mDetailUserFragment = DetailUserFragment()

        val mBundle = Bundle()
        mBundle.putParcelable(DetailUserFragment.EXTRA_USER, user)

        mDetailUserFragment.arguments = mBundle

        val mFragmentManager = parentFragmentManager
        mFragmentManager.beginTransaction().apply {
            replace(
                R.id.frame_container,
                mDetailUserFragment,
                DetailUserFragment::class.java.simpleName
            )
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}