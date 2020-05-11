# ijkplayer-live

> [ijkplayer](https://github.com/bilibili/ijkplayer) 播放直播流优化及秒开参数设置。

##  简单使用使用
1. 添加 `IjkVideoView` ；

```
    <cn.imtianx.ijkplayer.widget.media.IjkVideoView
        android:id="@+id/ijkVideoView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
        
```
2. 初始化设置相关参数

```
 // init ijkVideoView
ijkVideoView.apply {
    setRender(IjkVideoView.RENDER_SURFACE_VIEW)
    // 设置 view 宽高
    setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT)
    setVideoPath(videoPath)
    setOnInfoListener { iMediaPlayer, i, i2 ->
        Log.e("tx", "setOnInfoListener =======> i: $i\t\ti2: $i2")
        if (i != IMediaPlayer.STATE_PLAYING) {
            false
        } else {
            // 设置封面，避免首次打开显示黑屏，状态为 开始视屏渲染（ IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START ）时隐藏封面，
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
```
> 相关状态码可参见 **IMediaPlayer** 类。
>
## IMediaPlayer 推流秒开相关参数设置

1. 环路过滤，0开启，画面质量高,开销大；48关闭：

```
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 8);
```

2. 播放前探测时间,最大时间

```
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", 1);
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzemaxduration", 100);                  
```

3. 播放前探测 size,默认 1M，值小显示较快一点

```
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 1024*10);
```

4. 每处理完一个 package 后刷新 io 上下文

```
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "flush_packets", 1);
```

5. 播放预缓冲，开始可能会导致丢帧，0关闭

```
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);
```

6. 跳帧数，处理较慢时跳帧，保证播放流畅，音视屏同步

```
ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
```
> 完整配置参见 ：[IjkVideoView#createPlayer](https://github.com/imtianx/ijkplayer-live/blob/master/ijkplayer/src/main/java/cn/imtianx/ijkplayer/widget/media/IjkVideoView.java)

## 参考文章
[ijkplayer中遇到的问题汇总](https://juejin.im/post/5e79fc0d6fb9a07ca1373d20)





