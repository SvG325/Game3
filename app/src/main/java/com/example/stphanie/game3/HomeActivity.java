package com.example.stphanie.game3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DecimalFormat;


public class HomeActivity extends AppCompatActivity {
    public final String PLAYER = "PLAYER";
    private Character player;
    private TextView tvAlchLevel, tvAlchProgress;
    private TextView tvElmLevel, tvElmProgress;
    private TextView tvArcLevel, tvArcProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        player = (Character) bundle.getSerializable(PLAYER);

        tvAlchLevel = findViewById(R.id.tvAlchValue);
        tvAlchProgress = findViewById(R.id.tvAlchProgress);
        tvElmLevel = findViewById(R.id.tvElmValue);
        tvElmProgress = findViewById(R.id.tvElmProgress);
        tvArcLevel = findViewById(R.id.tvArcValue);
        tvArcProgress = findViewById(R.id.tvArcProgress);

        setViews();
    }

    private void setViews(){
        DecimalFormat df = new DecimalFormat("#.##");
        double progress;
        tvAlchLevel.setText(player.getAlchemyLevel() + "");
        progress =  (double) player.getAlchemyXp()/player.calcMaxXp(player.getAlchemyLevel())*100;
        tvAlchProgress.setText(df.format(progress) + "%");
        tvElmLevel.setText(player.getElementalLevel() + "");
        progress =  (double) player.getElementalXp()/player.calcMaxXp(player.getElementalLevel())*100;
        tvElmProgress.setText(df.format(progress) + "%");
        tvArcLevel.setText(player.getArcaneLevel() + "");
        progress =  (double) player.getArcaneXp()/player.calcMaxXp(player.getArcaneLevel())*100;
        tvArcProgress.setText(df.format(progress) + "%");
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYER, player);
        data.putExtras(bundle);
        setResult(RESULT_OK, data);
        finish();
    }


}
