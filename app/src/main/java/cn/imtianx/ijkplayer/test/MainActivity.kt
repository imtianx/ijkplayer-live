package cn.imtianx.ijkplayer.test

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import cn.imtianx.ijkplayer.widget.media.IRenderView
import cn.imtianx.ijkplayer.widget.media.IjkVideoView
import kotlinx.android.synthetic.main.activity_main.*
import tv.danmaku.ijk.media.player.IMediaPlayer

class MainActivity : AppCompatActivity() {

    private val videoPath =
        "ijkhttphook:http://pili-live-hdl.ulive.hzshuyu.com/ulivelive/1f729163f9d2593a.flv"

    //    private val videoPath = "ijkhttphook:http://img.imtianx.cn/video/login.mp4"
    private val coverUrl = "http://east.wangran.live/upload/head/20200430/5eaaeab87defb.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init ijkVideoView
        ijkVideoView.apply {
            setRender(IjkVideoView.RENDER_SURFACE_VIEW)
            setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT)
            setVideoPath(videoPath)
//            setEnableLooping(true)
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
//                iv_cover.isVisible = true
                true
            }
        }

//        videoView.setVideoURI(Uri.parse("android.resource://${packageName}/${R.raw.video}"))
//        videoView.setOnCompletionListener {
//            it.start()
//            it.isLooping = true
//        }
//        videoView.setOnInfoListener { mediaPlayer, i, i2 ->
//            Log.e("tx", "setOnInfoListener =======> i: $i\t\ti2: $i2")
//            false
//        }
//        videoView.setOnErrorListener { mediaPlayer, i, i2 ->
//            Log.e("tx", "setOnErrorListener =======> i: $i\t\ti2: $i2")
//            false
//        }


        btn_start.setOnClickListener {
            reInit()
            ijkVideoView.start()
//            videoView.start()
        }

        btn_pause.setOnClickListener {
            ijkVideoView.pause()
        }
    }


    private fun reInit() {
        ijkVideoView.apply {
            setRender(IjkVideoView.RENDER_SURFACE_VIEW)
            setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT)
            setVideoPath(videoPath)
            setEnableLooping(true)
            setOnInfoListener { _, i, i2 ->
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

    }

    override fun onResume() {
        super.onResume()
//        videoView.resume()
    }


    override fun onStop() {
        super.onStop()
//        videoView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        ijkVideoView.release(true)
    }
}
