package com.nizam.music_player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizam.music_player.databinding.ActivityPlayListSongsBinding

class PlayListSongsActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlayListSongsBinding
    private lateinit var playListName: String

    private val playListDB: PlaylistsDB by lazy {
        PlaylistsDB(this@PlayListSongsActivity,null)
    }

    companion object{
        var musicListPL:ArrayList<SongsData> = ArrayList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayListSongsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playListName = intent.getStringExtra("playListName").toString()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = playListName

        getAllSongs()
        setRecyclerViewAdapter()

        addSongsToPlaylist()
    }

    private fun addSongsToPlaylist() {

    }

    private fun getAllSongs() {
        musicListPL = playListDB.getPlayListSongs(playListName)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setRecyclerViewAdapter() {
        binding.playListSongsRecyclerView.setHasFixedSize(true)
        binding.playListSongsRecyclerView.setItemViewCacheSize(12)
        binding.playListSongsRecyclerView.layoutManager = LinearLayoutManager(this@PlayListSongsActivity)
        val adapter = PlayListSongRecyclerViewAdapter(this@PlayListSongsActivity, musicListPL)
        binding.playListSongsRecyclerView.adapter = adapter
    }
}