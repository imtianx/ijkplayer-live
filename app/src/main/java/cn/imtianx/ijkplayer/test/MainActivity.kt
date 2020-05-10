package cn.imtianx.ijkplayer.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.imtianx.ijkplayer.widget.media.IRenderView
import cn.imtianx.ijkplayer.widget.media.IjkVideoView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val videoPath = "http://pili-live-hdl.ulive.hzshuyu.com/ulivelive/da23001c617d8398.flv"
    private val coverUrl = "http://east.wangran.live/upload/head/20200430/5eaaeab87defb.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // init ijkVideoView
        ijkVideoView.apply {
            setRender(IjkVideoView.RENDER_SURFACE_VIEW)
            setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT)
            setVideoPath(videoPath)

            setOnInfoListener { iMediaPlayer, i, i2 ->
                Log.e("tx", "setOnInfoListener =======> i: $i\t\ti2: $i2")
                false
            }

            setOnErrorListener { iMediaPlayer, i, i2 ->
                Log.e("tx", "setOnErrorListener =======> i: $i\t\ti2: $i2")
                true
            }
        }

        btn_start.setOnClickListener {
            ijkVideoView.start()
        }

        btn_pause.setOnClickListener {
            ijkVideoView.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ijkVideoView.release(true)
    }

    override fun onResume() {
        super.onResume()
    }
}
