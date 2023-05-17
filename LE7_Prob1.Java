VideoView videoView = findViewById(R.id.videoView);
String videoUrl = "http://example.com/myvideo.mp4";
videoView.setVideoPath(videoUrl);
videoView.start();
