package com.ziko.dodopizza

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.ziko.dodopizza.databinding.ActivityLoginUserBinding

class LoginUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginUserBinding

    var phoneNumber = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        numberFormat()
        binding.apply {
            loginButton.setOnClickListener {
                if(nameEditText.text.toString().isNotEmpty() && phoneNumberEditText.text.toString().isNotEmpty()){
                    startActivity(Intent(this@LoginUserActivity, MainActivity::class.java))
                    Toast.makeText(this@LoginUserActivity, "saved", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
    @SuppressLint("ResourceAsColor")
    fun numberFormat() {

        binding.phoneNumberEditText.addTextChangedListener(object : TextWatcher {

            var isBackspaceClicked = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isBackspaceClicked = after < count
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!isBackspaceClicked) {

                    /* Let me prepare a StringBuilder to hold all digits of the edit text */
                    val digits = StringBuilder();

                    /* this is the phone StringBuilder that will hold the phone number */

                    val phone = StringBuilder();

                    /* let's take all characters from the edit text */
                    val chars: CharArray =
                        binding.phoneNumberEditText.text.toString().toCharArray();

                    /* a loop to extract all digits */
                    for (x in chars.indices) {
                        if (Character.isDigit(chars[x])) {
                            /* if its a digit append to digits string builder */
                            digits.append(chars[x]);
                        }
                    }


                    if (digits.toString().length >= 2) {
                        /* our phone formatting starts at the third character  and starts with the country code*/
                        var countryCode = String();

                        /* we build the country code */
                        countryCode += digits.toString().substring(0, 2) + " ";

                        /** and we append it to phone string builder **/
                        phone.append(countryCode);

                        /** if digits are more than or just 6, that means we already have our state code/region code **/
                        if (digits.toString().length >= 5) {

                            var firstThree = String();
                            /** we build the state/region code **/
                            firstThree += digits.toString().substring(2, 5) + " ";
                            /** we append the region code to phone **/
                            phone.append(firstThree);

                            /** the phone number will not go over 12 digits  if ten, set the limit to ten digits**/
                            if (digits.toString().length >= 7) {
                                var firstTwoNumbers = String()
                                firstTwoNumbers += digits.toString().substring(5, 7) + " "
                                phone.append(firstTwoNumbers)

                                if (digits.toString().length >= 9) {
                                    phone.append(digits.toString().substring(7, 9))
                                } else {
                                    phone.append(digits.toString().substring(7));
                                }
                            } else {
                                phone.append(digits.toString().substring(5));
                            }

                        } else {
                            phone.append(digits.toString().substring(2));
                        }
                        /** remove the watcher  so you can not capture the affectation you are going to make, to avoid infinite loop on text change **/
                        binding.phoneNumberEditText.removeTextChangedListener(this);
                        /** set the new text to the EditText **/
                        phoneNumber = phone.toString()
                        binding.phoneNumberEditText.setText(phone.toString());
                        /** bring the cursor to the end of input **/
                        binding.phoneNumberEditText.setSelection(binding.phoneNumberEditText.text.toString().length);
                        /* bring back the watcher and go on listening to change events */
                        binding.phoneNumberEditText.addTextChangedListener(this);

                    } else {
                        return;
                    }

                } else {
                    return
                }
            }

        })

    }
}