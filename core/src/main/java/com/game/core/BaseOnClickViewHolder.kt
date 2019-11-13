/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseOnClickViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener