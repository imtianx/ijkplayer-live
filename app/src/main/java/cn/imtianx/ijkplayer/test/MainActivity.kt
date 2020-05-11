package cn.imtianx.ijkplayer.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import cn.imtianx.ijkplayer.widget.media.IRenderView
import cn.imtianx.ijkplayer.widget.media.IjkVideoView
import kotlinx.android.synthetic.main.activity_main.*
import tv.danmaku.ijk.media.player.IMediaPlayer

class MainActivity : AppCompatActivity() {

    private val videoPath = "http://pili-live-hdl.ulive.hzshuyu.com/ulivelive/f656d81510ec95e2.flv"
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
                if (i != IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    false
                } else {
                    iv_cover.isVisible = false
                    false
                }
            }

            setOnErrorListener { iMediaPlayer, i, i2 ->
                Log.e("tx", "setOnErrorListener =======> i: $i\t\ti2: $i2")
                iv_cover.isVisible = true
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
