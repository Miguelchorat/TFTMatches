package com.example.tftmatches.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tftmatches.R;
import com.example.tftmatches.model.Match;
import com.example.tftmatches.model.Unit;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    List<Match> matches;
    private int layout;
    private Activity activity;
    private OnItemClickListener listener;

    public MatchAdapter(List<Match> matches, int layout, Activity activity, OnItemClickListener listener) {
        this.matches = matches;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.ViewHolder holder,final int i) {
        final Match match = matches.get(i);
        try{
            int placement = match.getPlacement();
            setPosition(holder,placement);
            List<String> traits = match.getTraits();

            for (int j=0; j<traits.size(); j++){
                setTrait(holder,traits.get(j),j);
                holder.traits.get(j).setVisibility(View.VISIBLE);
            }

            List<Unit> units = match.getUnits();

            int champCont = 0;
            for (Map.Entry<ImageView,List<ImageView>> entry : holder.units.entrySet()){
                if(champCont<units.size()){
                    ImageView img = entry.getKey();
                    List<ImageView> items = entry.getValue();
                    Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/9.24.2/img/champion/"+units.get(champCont).getName()+".png").into(img);
                    img.setVisibility(View.VISIBLE);
                    for (int z = 0;z<units.get(champCont).getItems().size();z++){
                        setItem(items.get(z),units.get(champCont).getItems().get(z));
                        items.get(z).setVisibility(View.VISIBLE);
                    }
                    champCont++;
                }
            }

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(match,i);
                }
            });

        }catch (NullPointerException e){
            System.out.println("Error en parsear partida");
        }

    }

    public void setItem(ImageView img,int item){

        switch (item){
            case 1:
                img.setImageResource(R.drawable.i1);
                break;
            case 5:
                img.setImageResource(R.drawable.i5);
                break;
            case 7:
                img.setImageResource(R.drawable.i7);
                break;
            case 3:
                img.setImageResource(R.drawable.i3);
                break;
            case 6:
                img.setImageResource(R.drawable.i6);
                break;
            case 2:
                img.setImageResource(R.drawable.i2);
                break;
            case 8:
                img.setImageResource(R.drawable.i8);
                break;
            case 9:
                img.setImageResource(R.drawable.i9);
                break;
            case 4:
                img.setImageResource(R.drawable.i4);
                break;
            case 89:
                img.setImageResource(R.drawable.i89);
                break;
            case 28:
                img.setImageResource(R.drawable.i28);
                break;
            case 16:
                img.setImageResource(R.drawable.i16);
                break;
            case 11:
                img.setImageResource(R.drawable.i11);
                break;
            case 66:
                img.setImageResource(R.drawable.i66);
                break;
            case 88:
                img.setImageResource(R.drawable.i88);
                break;
            case 45:
                img.setImageResource(R.drawable.i45);
                break;
            case 78:
                img.setImageResource(R.drawable.i78);
                break;
            case 12:
                img.setImageResource(R.drawable.i12);
                break;
            case 15:
                img.setImageResource(R.drawable.i15);
                break;
            case 23:
                img.setImageResource(R.drawable.i23);
                break;
            case 49:
                img.setImageResource(R.drawable.i49);
                break;
            case 13:
                img.setImageResource(R.drawable.i13);
                break;
            case 46:
                img.setImageResource(R.drawable.i46);
                break;
            case 59:
                img.setImageResource(R.drawable.i59);
                break;
            case 38:
                img.setImageResource(R.drawable.i38);
                break;
            case 19:
                img.setImageResource(R.drawable.i19);
                break;
            case 36:
                img.setImageResource(R.drawable.i36);
                break;
            case 39:
                img.setImageResource(R.drawable.i39);
                break;
            case 35:
                img.setImageResource(R.drawable.i35);
                break;
            case 34:
                img.setImageResource(R.drawable.i34);
                break;
            case 48:
                img.setImageResource(R.drawable.i48);
                break;
            case 37:
                img.setImageResource(R.drawable.i37);
                break;
            case 25:
                img.setImageResource(R.drawable.i25);
                break;
            case 69:
                img.setImageResource(R.drawable.i69);
                break;
            case 33:
                img.setImageResource(R.drawable.i33);
                break;
            case 22:
                img.setImageResource(R.drawable.i22);
                break;
            case 57:
                img.setImageResource(R.drawable.i57);
                break;
            case 47:
                img.setImageResource(R.drawable.i47);
                break;
            case 29:
                img.setImageResource(R.drawable.i29);
                break;
            case 26:
                img.setImageResource(R.drawable.i26);
                break;
            case 44:
                img.setImageResource(R.drawable.i44);
                break;
            case 14:
                img.setImageResource(R.drawable.i14);
                break;
            case 24:
                img.setImageResource(R.drawable.i24);
                break;
            case 56:
                img.setImageResource(R.drawable.i56);
                break;
            case 68:
                img.setImageResource(R.drawable.i68);
                break;
            case 99:
                img.setImageResource(R.drawable.i99);
                break;
            case 55:
                img.setImageResource(R.drawable.i55);
                break;
            case 27:
                img.setImageResource(R.drawable.i27);
                break;
            case 79:
                img.setImageResource(R.drawable.i79);
                break;
            case 58:
                img.setImageResource(R.drawable.i58);
                break;
            case 77:
                img.setImageResource(R.drawable.i77);
                break;
            case 18:
                img.setImageResource(R.drawable.i18);
                break;
            case 17:
                img.setImageResource(R.drawable.i17);
                break;
            case 67:
                img.setImageResource(R.drawable.i67);
                break;
            case 10201:
                img.setImageResource(R.drawable.i10201);
                break;
            case 10202:
                img.setImageResource(R.drawable.i10202);
                break;
            case 10203:
                img.setImageResource(R.drawable.i10203);
                break;
            case 10204:
                img.setImageResource(R.drawable.i10204);
                break;
            default:

        }
    }

    public void setTrait(MatchAdapter.ViewHolder holder,String t,int i){

        switch (t.toLowerCase()){
            case "alchemist":
                holder.traits.get(i).setImageResource(R.drawable.alchemist);
                break;
            case "soulbound":
                holder.traits.get(i).setImageResource(R.drawable.soulbound);
                break;
            case "set2_assassin":
                holder.traits.get(i).setImageResource(R.drawable.assassin);
                break;
            case "avatar":
                holder.traits.get(i).setImageResource(R.drawable.avatar);
                break;
            case "berserker":
                holder.traits.get(i).setImageResource(R.drawable.berserker);
                break;
            case "set2_blademaster":
                holder.traits.get(i).setImageResource(R.drawable.blademaster);
                break;
            case "cloud":
                holder.traits.get(i).setImageResource(R.drawable.cloud);
                break;
            case "crystal":
                holder.traits.get(i).setImageResource(R.drawable.crystal);
                break;
            case "desert":
                holder.traits.get(i).setImageResource(R.drawable.desert);
                break;
            case "druid":
                holder.traits.get(i).setImageResource(R.drawable.druid);
                break;
            case "electric":
                holder.traits.get(i).setImageResource(R.drawable.electric);
                break;
            case "set2_glacial":
                holder.traits.get(i).setImageResource(R.drawable.glacial);
                break;
            case "inferno":
                holder.traits.get(i).setImageResource(R.drawable.infierno);
                break;
            case "light":
                holder.traits.get(i).setImageResource(R.drawable.light);
                break;
            case "mage":
                holder.traits.get(i).setImageResource(R.drawable.mage);
                break;
            case "mountain":
                holder.traits.get(i).setImageResource(R.drawable.mountain);
                break;
            case "mystic":
                holder.traits.get(i).setImageResource(R.drawable.mystic);
                break;
            case "ocean":
                holder.traits.get(i).setImageResource(R.drawable.ocean);
                break;
            case "poison":
                holder.traits.get(i).setImageResource(R.drawable.poison);
                break;
            case "predator":
                holder.traits.get(i).setImageResource(R.drawable.predator);
                break;
            case "set2_ranger":
                holder.traits.get(i).setImageResource(R.drawable.ranger);
                break;
            case "shadow":
                holder.traits.get(i).setImageResource(R.drawable.shadow);
                break;
            case "metal":
                holder.traits.get(i).setImageResource(R.drawable.steel);
                break;
            case "summoner":
                holder.traits.get(i).setImageResource(R.drawable.summoner);
                break;
            case "warden":
                holder.traits.get(i).setImageResource(R.drawable.warden);
                break;
            case "woodland":
                holder.traits.get(i).setImageResource(R.drawable.woodland);
                break;
            case "lunar":
                holder.traits.get(i).setImageResource(R.drawable.lunar);
                break;
        }
    }

    public void setPosition(MatchAdapter.ViewHolder holder,int placement){

        switch (placement){
            case 1:
                holder.position.setText(R.string.position1);
                break;
            case 2:
                holder.position.setText(R.string.position2);
                break;
            case 3:
                holder.position.setText(R.string.position3);
                break;
            case 4:
                holder.position.setText(R.string.position4);
                break;
            case 5:
                holder.position.setText(R.string.position5);
                break;
            case 6:
                holder.position.setText(R.string.position6);
                break;
            case 7:
                holder.position.setText(R.string.position7);
                break;
            case 8:
                holder.position.setText(R.string.position8);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(matches==null)
            return 0;
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        TextView position;
        List<ImageView> traits;
        Map<ImageView,List<ImageView>> units;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.match);
            position = itemView.findViewById(R.id.position);
            units = new LinkedHashMap<>();
            traits = new ArrayList<>();
            traits.add((ImageView) itemView.findViewById(R.id.trait1));
            traits.add((ImageView) itemView.findViewById(R.id.trait2));
            traits.add((ImageView) itemView.findViewById(R.id.trait3));
            traits.add((ImageView) itemView.findViewById(R.id.trait4));
            traits.add((ImageView) itemView.findViewById(R.id.trait5));
            traits.add((ImageView) itemView.findViewById(R.id.trait6));
            traits.add((ImageView) itemView.findViewById(R.id.trait7));
            traits.add((ImageView) itemView.findViewById(R.id.trait8));

            List unit1 = new ArrayList();
            unit1.add(itemView.findViewById(R.id.unit1item1));
            unit1.add(itemView.findViewById(R.id.unit1item2));
            unit1.add(itemView.findViewById(R.id.unit1item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit1),unit1);

            List unit2 = new ArrayList();
            unit2.add(itemView.findViewById(R.id.unit2item1));
            unit2.add(itemView.findViewById(R.id.unit2item2));
            unit2.add(itemView.findViewById(R.id.unit2item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit2),unit2);

            List unit3 = new ArrayList();
            unit3.add(itemView.findViewById(R.id.unit3item1));
            unit3.add(itemView.findViewById(R.id.unit3item2));
            unit3.add(itemView.findViewById(R.id.unit3item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit3),unit3);

            List unit4 = new ArrayList();
            unit4.add(itemView.findViewById(R.id.unit4item1));
            unit4.add(itemView.findViewById(R.id.unit4item2));
            unit4.add(itemView.findViewById(R.id.unit4item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit4),unit4);

            List unit5 = new ArrayList();
            unit5.add(itemView.findViewById(R.id.unit5item1));
            unit5.add(itemView.findViewById(R.id.unit5item2));
            unit5.add(itemView.findViewById(R.id.unit5item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit5),unit5);

            List unit6 = new ArrayList();
            unit6.add(itemView.findViewById(R.id.unit6item1));
            unit6.add(itemView.findViewById(R.id.unit6item2));
            unit6.add(itemView.findViewById(R.id.unit6item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit6),unit6);

            List unit7 = new ArrayList();
            unit7.add(itemView.findViewById(R.id.unit7item1));
            unit7.add(itemView.findViewById(R.id.unit7item2));
            unit7.add(itemView.findViewById(R.id.unit7item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit7),unit7);

            List unit8 = new ArrayList();
            unit8.add(itemView.findViewById(R.id.unit8item1));
            unit8.add(itemView.findViewById(R.id.unit8item2));
            unit8.add(itemView.findViewById(R.id.unit8item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit8),unit8);

            List unit9 = new ArrayList();
            unit9.add(itemView.findViewById(R.id.unit9item1));
            unit9.add(itemView.findViewById(R.id.unit9item2));
            unit9.add(itemView.findViewById(R.id.unit9item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit9),unit9);

            List unit10 = new ArrayList();
            unit10.add(itemView.findViewById(R.id.unit10item1));
            unit10.add(itemView.findViewById(R.id.unit10item2));
            unit10.add(itemView.findViewById(R.id.unit10item3));
            units.put((ImageView) itemView.findViewById(R.id.imgunit10),unit10);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(Match match, int i);
    }
}
