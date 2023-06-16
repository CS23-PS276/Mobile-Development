// Generated by view binder compiler. Do not edit!
package com.cs23_ps276.sahabatlansia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cs23_ps276.sahabatlansia.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnAddLansia;

  @NonNull
  public final RelativeLayout relativeLayout;

  @NonNull
  public final RecyclerView rvCaregiver;

  @NonNull
  public final TextView textView6;

  private FragmentProfileBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnAddLansia,
      @NonNull RelativeLayout relativeLayout, @NonNull RecyclerView rvCaregiver,
      @NonNull TextView textView6) {
    this.rootView = rootView;
    this.btnAddLansia = btnAddLansia;
    this.relativeLayout = relativeLayout;
    this.rvCaregiver = rvCaregiver;
    this.textView6 = textView6;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_lansia;
      Button btnAddLansia = ViewBindings.findChildViewById(rootView, id);
      if (btnAddLansia == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      RelativeLayout relativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.rv_caregiver;
      RecyclerView rvCaregiver = ViewBindings.findChildViewById(rootView, id);
      if (rvCaregiver == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      return new FragmentProfileBinding((ConstraintLayout) rootView, btnAddLansia, relativeLayout,
          rvCaregiver, textView6);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
