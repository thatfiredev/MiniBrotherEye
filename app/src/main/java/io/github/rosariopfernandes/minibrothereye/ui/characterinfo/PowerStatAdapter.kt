package io.github.rosariopfernandes.minibrothereye.ui.characterinfo

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ramijemli.percentagechartview.PercentageChartView
import io.github.rosariopfernandes.minibrothereye.R
import io.github.rosariopfernandes.minibrothereye.model.PowerStats

class PowerStatAdapter(
    private val powerStats: PowerStats
) : RecyclerView.Adapter<PowerStatAdapter.PowerStatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PowerStatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_powerstat, parent, false)
        return PowerStatViewHolder(view)
    }

    override fun getItemCount() = 6

    override fun onBindViewHolder(holder: PowerStatViewHolder, position: Int) {
        when (position) {
            0 -> holder.bindTo(R.string.label_intelligence, powerStats.intelligence, R.color.colorPurple)
            1 -> holder.bindTo(R.string.label_strength, powerStats.strength, R.color.colorRed)
            2 -> holder.bindTo(R.string.label_speed, powerStats.speed, R.color.colorAmber)
            3 -> holder.bindTo(R.string.label_durability, powerStats.durability, R.color.colorYellow)
            4 -> holder.bindTo(R.string.label_power, powerStats.power, R.color.colorGreen)
            5 -> holder.bindTo(R.string.label_combat, powerStats.combat, R.color.colorIndigo)
        }
    }

    class PowerStatViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val pbStat = v.findViewById<PercentageChartView>(R.id.pbStat)
        private val tvStatLabel = v.findViewById<TextView>(R.id.tvStatLabel)

        fun bindTo(labelResId: Int, stat: Int, colorResId: Int) {
            tvStatLabel.setText(labelResId)
            pbStat.setProgress(stat.toFloat(), true)
            pbStat.progressColor = ContextCompat.getColor(pbStat.context, colorResId)
        }
    }
}