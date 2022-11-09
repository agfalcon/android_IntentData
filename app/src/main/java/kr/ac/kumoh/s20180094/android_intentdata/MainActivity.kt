package kr.ac.kumoh.s20180094.android_intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.s20180094.android_intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val KEY_NAME = "image"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRabit.setOnClickListener(this)
        binding.btnSmile.setOnClickListener(this)

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode != RESULT_OK)
                return@registerForActivityResult

            val result = it.data?.getIntExtra(ImageActivity.RESULT_NAME, ImageActivity.NONE)

            val str = when(result){
                ImageActivity.LIKE -> "좋아요"
                ImageActivity.DISLIKE -> "싫어요"
                else -> ""
            }
            val image = it.data?.getStringExtra(ImageActivity.IMAGE_NAME)
            when(image){
                "rabit" -> binding.btnRabit.text = "토끼 ($str)"
                "smile" -> binding.btnSmile.text = "스마일 ($str)"
                else -> return@registerForActivityResult
            }
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when(v?.id){
            binding.btnRabit.id -> "rabit"
            binding.btnSmile.id -> "smile"
            else -> return
        }
        intent.putExtra(KEY_NAME, value)
        launcher.launch(intent)
    }
}