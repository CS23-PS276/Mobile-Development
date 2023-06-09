// Generated by view binder compiler. Do not edit!
package com.cs23_ps276.sahabatlansia.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.cs23_ps276.sahabatlansia.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView confirmPassword;

  @NonNull
  public final TextInputEditText edConfirmPassword;

  @NonNull
  public final TextInputEditText edEmail;

  @NonNull
  public final TextInputEditText edPassword;

  @NonNull
  public final TextInputEditText edUsername;

  @NonNull
  public final TextView email;

  @NonNull
  public final TextView haveAccount;

  @NonNull
  public final TextView password;

  @NonNull
  public final Button registerButton;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextInputLayout tilConfirmPassword;

  @NonNull
  public final TextInputLayout tilEmail;

  @NonNull
  public final TextInputLayout tilPassword;

  @NonNull
  public final TextInputLayout tilUsername;

  @NonNull
  public final TextView username;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView confirmPassword, @NonNull TextInputEditText edConfirmPassword,
      @NonNull TextInputEditText edEmail, @NonNull TextInputEditText edPassword,
      @NonNull TextInputEditText edUsername, @NonNull TextView email, @NonNull TextView haveAccount,
      @NonNull TextView password, @NonNull Button registerButton, @NonNull TextView textView4,
      @NonNull TextInputLayout tilConfirmPassword, @NonNull TextInputLayout tilEmail,
      @NonNull TextInputLayout tilPassword, @NonNull TextInputLayout tilUsername,
      @NonNull TextView username) {
    this.rootView = rootView;
    this.confirmPassword = confirmPassword;
    this.edConfirmPassword = edConfirmPassword;
    this.edEmail = edEmail;
    this.edPassword = edPassword;
    this.edUsername = edUsername;
    this.email = email;
    this.haveAccount = haveAccount;
    this.password = password;
    this.registerButton = registerButton;
    this.textView4 = textView4;
    this.tilConfirmPassword = tilConfirmPassword;
    this.tilEmail = tilEmail;
    this.tilPassword = tilPassword;
    this.tilUsername = tilUsername;
    this.username = username;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.confirm_password;
      TextView confirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (confirmPassword == null) {
        break missingId;
      }

      id = R.id.ed_confirm_password;
      TextInputEditText edConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (edConfirmPassword == null) {
        break missingId;
      }

      id = R.id.ed_email;
      TextInputEditText edEmail = ViewBindings.findChildViewById(rootView, id);
      if (edEmail == null) {
        break missingId;
      }

      id = R.id.ed_password;
      TextInputEditText edPassword = ViewBindings.findChildViewById(rootView, id);
      if (edPassword == null) {
        break missingId;
      }

      id = R.id.ed_username;
      TextInputEditText edUsername = ViewBindings.findChildViewById(rootView, id);
      if (edUsername == null) {
        break missingId;
      }

      id = R.id.email;
      TextView email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.haveAccount;
      TextView haveAccount = ViewBindings.findChildViewById(rootView, id);
      if (haveAccount == null) {
        break missingId;
      }

      id = R.id.password;
      TextView password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.registerButton;
      Button registerButton = ViewBindings.findChildViewById(rootView, id);
      if (registerButton == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.til_confirm_password;
      TextInputLayout tilConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (tilConfirmPassword == null) {
        break missingId;
      }

      id = R.id.til_email;
      TextInputLayout tilEmail = ViewBindings.findChildViewById(rootView, id);
      if (tilEmail == null) {
        break missingId;
      }

      id = R.id.til_password;
      TextInputLayout tilPassword = ViewBindings.findChildViewById(rootView, id);
      if (tilPassword == null) {
        break missingId;
      }

      id = R.id.til_username;
      TextInputLayout tilUsername = ViewBindings.findChildViewById(rootView, id);
      if (tilUsername == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, confirmPassword,
          edConfirmPassword, edEmail, edPassword, edUsername, email, haveAccount, password,
          registerButton, textView4, tilConfirmPassword, tilEmail, tilPassword, tilUsername,
          username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
