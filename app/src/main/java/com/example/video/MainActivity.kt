package com.example.video
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController

import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView



class MainActivity : AppCompatActivity() {
    var simpleVideoView: VideoView? = null
    var text : TextView ? = null
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        simpleVideoView = findViewById<View>(R.id.videoView) as VideoView?
        text = findViewById(R.id.text1)
        if (mediaControls == null) {
            mediaControls = MediaController(this)
            mediaControls!!.setAnchorView(this.simpleVideoView)
        }

        simpleVideoView!!.setMediaController(mediaControls)


        simpleVideoView!!.setVideoURI(
            Uri.parse("android.resource://"
                + packageName + "/" + R.raw.varisu))

        simpleVideoView!!.requestFocus()


        simpleVideoView?.start()
        simpleVideoView?.pause()


        simpleVideoView!!.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video completed",
                Toast.LENGTH_LONG).show()
        }
        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occurred " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.

        val id = item.itemId
        if (id == R.id.unhide) {

            simpleVideoView?.visibility = View.VISIBLE
            return true
        }

        if (id == R.id.ChangeColor) {
            text?.setTextColor(Color.RED)
            Toast.makeText(this, "YELLOW Clicked", Toast.LENGTH_LONG).show()
            return true
        }

        if(id == R.id.ChangeFont)
        {
            text?.textSize = resources.getDimension(R.dimen.small)
        }

        if(id == R.id.Play)
        {
            simpleVideoView?.start()
        }

        if(id == R.id.exit)
        {
            finishAffinity()
        }
        return super.onOptionsItemSelected(item)

    }
}