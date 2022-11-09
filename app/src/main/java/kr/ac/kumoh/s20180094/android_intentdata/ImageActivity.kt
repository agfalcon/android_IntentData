package kr.ac.kumoh.s20180094.android_intentdata

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kr.ac.kumoh.s20180094.android_intentdata.databinding.ActivityImageBinding


class ImageActivity : AppCompatActivity(), OnClickListener {

    companion object{
        const val IMAGE_NAME = "image"
        const val RESULT_NAME = "result"

        const val LIKE = 10
        const val DISLIKE = 20
        const val NONE = 0
    }
    private lateinit var binding: ActivityImageBinding
    private var image : String?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        image = intent.getStringExtra(MainActivity.KEY_NAME)
        val res = when(image){
            "rabit" -> R.drawable.rabit
            "smile" -> R.drawable.smile
            else -> R.drawable.ic_launcher_foreground
        }
        binding.imgSelect.setImageResource(res)

        binding.btnLike.setOnClickListener(this)
        binding.btnDislike.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent()
        val value = when(v?.id){
            binding.btnLike.id -> LIKE
            binding.btnDislike.id -> DISLIKE
            else -> NONE
        }
        intent.putExtra(IMAGE_NAME, image)
        intent.putExtra(RESULT_NAME, value)
        setResult(RESULT_OK, intent)
        finish()
    }
}