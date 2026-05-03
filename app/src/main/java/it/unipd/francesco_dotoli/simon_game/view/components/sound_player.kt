package it.unipd.francesco_dotoli.simon_game.view.components

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import androidx.compose.ui.graphics.Color
import it.unipd.francesco_dotoli.simon_game.colorsList
import kotlin.math.exp
import kotlin.math.sin

fun playSound(color: Color) {
    val frequency = getFrequencyFromColor(color)
    if (frequency == 0.0) return

//!!!!!!!!!!!!!!!!! Generato da gemini per far sembrare un suono orecchiabile e simil pianoforre !!!!!!!!
    val rate = 48000
    val duration = 0.8
    val numSamples = (rate * duration).toInt()
    val sound = ShortArray(numSamples * 2) // Two channels for Stereo

    for (i in 0..<numSamples) {
        val time = i / rate.toDouble()

        // Piano-like ADSR Envelope: Very fast attack and exponential decay
        val attackTime = 0.005
        val envelope = if (time < attackTime) {
            time / attackTime
        } else {
            exp(-3.0 * (time - attackTime))
        }

        // Multiple harmonics to enrich the timbre
        val w = 2.0 * Math.PI * frequency
        val sample = (
                sin(w * time) * 1.0 +         // Fundamental
                        sin(2.0 * w * time) * 0.4 +   // 2nd Harmonic
                        sin(3.0 * w * time) * 0.2 +   // 3rd Harmonic
                        sin(4.0 * w * time) * 0.1     // 4th Harmonic
                ) * envelope

        // Normalize (1.0 + 0.4 + 0.2 + 0.1 = 1.7) and convert to 16-bit PCM
        val pcmValue = (sample * Short.MAX_VALUE / 1.7).toInt().toShort()
        sound[i * 2] = pcmValue     // Left channel
        sound[i * 2 + 1] = pcmValue // Right channel
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    val audioTrack = AudioTrack.Builder()
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        .setAudioFormat(
            AudioFormat.Builder()
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .setSampleRate(rate)
                .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                .build()
        )
        .setBufferSizeInBytes(sound.size * 2)
        .setTransferMode(AudioTrack.MODE_STATIC)
        .build()

    audioTrack.write(sound, 0, sound.size)
    audioTrack.play()

    Thread.sleep((duration * 1000).toLong())
    audioTrack.release()
}

fun getFrequencyFromColor(color: Color): Double {
    return when (color) {
        colorsList[0] -> 440.0 // La
        colorsList[1] -> 493.88 // Si
        colorsList[2] -> 523.25 // Do
        colorsList[3] -> 587.33 // Re
        colorsList[4] -> 659.25 // Mi
        colorsList[5] -> 698.46 // Fa
        else -> 0.0
    }
}
