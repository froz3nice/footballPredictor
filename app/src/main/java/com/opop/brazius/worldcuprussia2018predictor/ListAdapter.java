package com.opop.brazius.worldcuprussia2018predictor;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juozas on 2018.03.26.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Team> dataSet;

    private Context context;
    private final int GROUP_ITEM = 5;
    private final int HEADER_ITEM = 0;
    private final int FINAL_ITEM = 420;
    private final int ROUND16_ITEM = 16;
    private final int ROUND8_ITEM = 8;
    private final int SEMIFINAL_ITEM = 4;
    private final int GROUP_COUNT = 8;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView group;
        TextView informer;
        TextView name1, name2, name3, name4;
        ImageView img1, img2, img3, img4;
        Button btn1, btn2, btn3, btn4;
        boolean isFirst = true;
        boolean isSecond = false;

        public ViewHolder(final View itemView, TextView group, TextView informer, TextView name1, TextView name2, ImageView img1, ImageView img2, Button btn1, Button btn2) {
            super(itemView);
            this.group = group;
            this.informer = informer;
            this.name1 = name1;
            this.name2 = name2;
            this.img1 = img1;
            this.img2 = img2;
            this.btn1 = btn1;
            this.btn2 = btn2;
        }

        public ViewHolder(final View itemView, TextView group, TextView informer, TextView name1, TextView name2, TextView name3, TextView name4, ImageView img1, ImageView img2, ImageView img3, ImageView img4, Button btn1, Button btn2, Button btn3, Button btn4) {
            super(itemView);
            this.group = group;
            this.informer = informer;
            this.name1 = name1;
            this.name2 = name2;
            this.name3 = name3;
            this.name4 = name4;
            this.img1 = img1;
            this.img2 = img2;
            this.img3 = img3;
            this.img4 = img4;
            this.btn1 = btn1;
            this.btn2 = btn2;
            this.btn3 = btn3;
            this.btn4 = btn4;
        }
    }

    public class WinnerHolder extends RecyclerView.ViewHolder {
        ImageView flag;
        ImageView rate;
        ImageView share;
        TextView name;

        public WinnerHolder(final View itemView, ImageView flag, TextView name,ImageView rate, ImageView share) {
            super(itemView);
            this.flag = flag;
            this.name = name;
            this.rate = rate;
            this.share = share;
        }
    }
    ViewAddedListener listener;
    public ListAdapter(Context context,MainActivity activity) {
        this.context = context;
        listener = activity;
        dataSet = new ArrayList<>();
        dataSet.add(new Team());
        dataSet.add(new Team("Group A", "Russia", "Saudi Arabia", "Egypt", "Uruguay",
                R.drawable.rus, R.drawable.saudi, R.drawable.egy, R.drawable.uru));
        dataSet.add(new Team("Group B", "Portugal", "Spain", "Morocco", "Iran",
                R.drawable.port, R.drawable.spain, R.drawable.morocco, R.drawable.iran));
        dataSet.add(new Team("Group C", "France", "Australia", "Peru", "Denmark",
                R.drawable.france, R.drawable.australia, R.drawable.peru, R.drawable.denmark));
        dataSet.add(new Team("Group D", "Argentina", "Iceland", "Croatia", "Nigeria",
                R.drawable.arge, R.drawable.iceland, R.drawable.cro, R.drawable.nigeria));
        dataSet.add(new Team("Group E", "Brasil", "Switzerland", "Costa Rica", "Serbia",
                R.drawable.brasil, R.drawable.swiss, R.drawable.costa, R.drawable.serb));
        dataSet.add(new Team("Group F", "Germany", "Mexico", "Sweden", "South Korea",
                R.drawable.ger, R.drawable.mexico, R.drawable.swe, R.drawable.korea));
        dataSet.add(new Team("Group G", "Belgium", "Panama", "Tunisia", "England",
                R.drawable.belg, R.drawable.panama, R.drawable.tunis, R.drawable.england));
        dataSet.add(new Team("Group H", "Poland", "Senegal", "Colombia", "Japan",
                R.drawable.poland, R.drawable.senegal, R.drawable.colombia, R.drawable.japan));
    }

    private ViewHolder getPopulatedVH(View v,boolean isFinal) {
        TextView group = v.findViewById(R.id.tv_groupName);
        TextView informer = v.findViewById(R.id.tv_informer);
        View first = v.findViewById(R.id.include1);
        View second = v.findViewById(R.id.include2);

        TextView name1 = first.findViewById(R.id.tv_team_name);
        TextView name2 = second.findViewById(R.id.tv_team_name);
        ImageView img1 = first.findViewById(R.id.iv_flag);
        ImageView img2 = second.findViewById(R.id.iv_flag);
        Button btn1 = first.findViewById(R.id.btn_team_add);
        Button btn2 = second.findViewById(R.id.btn_team_add);
        if(!isFinal) {
            View third = v.findViewById(R.id.include3);
            View forth = v.findViewById(R.id.include4);
            TextView name3 = third.findViewById(R.id.tv_team_name);
            TextView name4 = forth.findViewById(R.id.tv_team_name);
            ImageView img3 = third.findViewById(R.id.iv_flag);
            ImageView img4 = forth.findViewById(R.id.iv_flag);

            Button btn3 = third.findViewById(R.id.btn_team_add);
            Button btn4 = forth.findViewById(R.id.btn_team_add);
            return new ViewHolder(v, group, informer, name1, name2, name3, name4, img1, img2, img3, img4, btn1, btn2, btn3, btn4);
        }
        return new ViewHolder(v, group, informer, name1, name2, img1, img2, btn1, btn2);
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public HeaderViewHolder(View headerView) {
            super(headerView);
            this.title = (TextView) headerView.findViewById(R.id.textView);
            Typeface typeFace=Typeface.createFromAsset(context.getAssets(), "fonts/arcade.otf");
            this.title.setTypeface(typeFace);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        if (Util.isHeader(viewType)) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.section_header, parent, false);
            return new HeaderViewHolder(v);
        }
        // create a new view
        else if (Util.isGroup(viewType)) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.groups, parent, false);
            return getPopulatedVH(v,false);
        } else if (Util.isRound16(viewType) || Util.isRound8(viewType) || Util.isSemiFinal(viewType)) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.round16, parent, false);
            return getPopulatedVH(v,false);
        } else if (Util.isFinal(viewType)) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.final_round, parent, false);
            return getPopulatedVH(v,true);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.winner, parent, false);
            ImageView flag = v.findViewById(R.id.iv_winner);
            TextView name = v.findViewById(R.id.tv_winner_country);
            ImageView rate = v.findViewById(R.id.iv_rate);
            ImageView share = v.findViewById(R.id.iv_share);

            return new WinnerHolder(v, flag, name,rate,share);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, int position) {
        int pos = holder1.getAdapterPosition();
        Team item = dataSet.get(pos);
        if (Util.isHeader(pos)) {
            HeaderViewHolder holder = (HeaderViewHolder) holder1;
            switch (pos) {
                case 0:
                    holder.title.setText("GROUPS");
                    break;
                case 9:
                    holder.title.setText("ROUND 16");
                    break;
                case 14:
                    holder.title.setText("QUARTER-FINAL");
                    break;
                case 17:
                    holder.title.setText("SEMI-FINAL");
                    break;
                case 19:
                    holder.title.setText("FINAL");
                    break;
            }
        }else if(Util.isWinnerView(pos)){
            WinnerHolder holder = (WinnerHolder) holder1;
            bindWinnerView(holder);
        } else {
            ViewHolder holder = (ViewHolder) holder1;
            Log.d("position", String.valueOf(pos));
            holder.group.setText(item.getGroup());
            holder.img1.setImageResource(item.flagRes1);
            holder.img1.setTag(item.flagRes1);
            holder.img2.setImageResource(item.flagRes2);
            holder.img2.setTag(item.flagRes2);
            holder.name1.setText(item.getName1());
            holder.name2.setText(item.getName2());
            if(!Util.isFinal(pos)) {
                holder.img3.setImageResource(item.flagRes3);
                holder.img3.setTag(item.flagRes3);
                holder.img4.setImageResource(item.flagRes4);
                holder.img4.setTag(item.flagRes4);
                holder.name3.setText(item.getName3());
                holder.name4.setText(item.getName4());
            }
            if (Util.isGroup(pos)) {
                bindGroupButtons(holder);
            } else if (Util.isRound16(pos)) {
                bindRound16Buttons(holder);
            } else if (Util.isRound8(pos)) {
                bindRound8Buttons(holder);
            } else if (Util.isSemiFinal(pos)) {
                bindSemiFinalButtons(holder);
            } else if (Util.isFinal(pos)) {
                bindFinalButtons(holder);
            }
        }
    }

    private void bindWinnerView(WinnerHolder holder) {
        holder.name.setText(dataSet.get(20).getFinalWinner());
        holder.flag.setImageResource(dataSet.get(20).getFinalWinnerRes());
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder str = new StringBuilder();
                for(Team tm : dataSet){
                    str.append(tm.toString());
                }
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,str.toString() );
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });
        holder.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    context.startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
            }
        });
    }

    private void bindFinalButtons(final ViewHolder holder) {
        if (dataSet.get(holder.getAdapterPosition()).getSemiFinalWinner().equals("")) {
            resetWinner(holder.btn1, holder.btn2);
        }

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFinalUI(holder.btn1, holder.btn2, holder, 1);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFinalUI(holder.btn2, holder.btn1, holder, 1);
            }
        });
    }

    private void updateFinalUI(Button btn1, Button btn2, ViewHolder holder, int i) {
        int position = holder.getAdapterPosition();
        if (btn1.getText().toString().equals("+")) {
            setWinner(btn1, btn2);
            dataSet.get(position).setFinalWinner(Util.getTeamByButton(btn1, holder));
            dataSet.get(position).setFinalWinnerRes(Util.getFlagByButton(btn1, holder));
            addWinnerData();
        } else if (btn1.getText().toString().equals("WIN")) {
            resetWinner(btn1, btn2);
            dataSet.get(position).setFinalWinner("");
            dataSet.get(position).setFinalWinnerRes(0);
            removeWinnerData();
        }
    }

    private void addWinnerData() {
        dataSet.add(getWinnerTeam());
        notifyItemRangeInserted(21, 1);
        listener.onWinnerAdded();
    }

    private Team getWinnerTeam() {
        return new Team("Winner",dataSet.get(20).getFinalWinner(),dataSet.get(20).getFinalWinnerRes());
    }

    private void removeWinnerData() {
        int size = dataSet.size();
        dataSet.subList(21, dataSet.size()).clear();
        notifyItemRangeRemoved(21, size - 21);
    }

    private void bindSemiFinalButtons(final ViewHolder holder) {
        if (dataSet.get(holder.getAdapterPosition()).getSemiFinalWinner().equals("")) {
            resetWinner(holder.btn1, holder.btn2);
        }
        if (dataSet.get(holder.getAdapterPosition()).getSemiFinalWinner2().equals("")) {
            resetWinner(holder.btn3, holder.btn4);
        }
        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSemiFinalUI(holder.btn1, holder.btn2, holder, 1);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSemiFinalUI(holder.btn2, holder.btn1, holder, 1);
            }
        });
        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSemiFinalUI(holder.btn3, holder.btn4, holder, 2);
            }
        });
        holder.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSemiFinalUI(holder.btn4, holder.btn3, holder, 2);
            }
        });
    }

    private void updateSemiFinalUI(Button btn1, Button btn2, ViewHolder holder, int section) {
        int position = holder.getAdapterPosition();
        if (btn1.getText().toString().equals("+")) {
            setWinner(btn1, btn2);
            if (section == 1) {
                dataSet.get(position).setSemiFinalWinner(Util.getTeamByButton(btn1, holder));
                dataSet.get(position).setSemiFinalWinnerRes(Util.getFlagByButton(btn1, holder));
            } else {
                dataSet.get(position).setSemiFinalWinner2(Util.getTeamByButton(btn1, holder));
                dataSet.get(position).setSemiFinalWinner2Res(Util.getFlagByButton(btn1, holder));
            }
            if (checkIfSemiFinalDone()) {
                addFinalData();
            }
        } else if (btn1.getText().toString().equals("WIN")) {
            resetWinner(btn1, btn2);
            if (section == 1) {
                dataSet.get(position).setSemiFinalWinner("");
                dataSet.get(position).setSemiFinalWinnerRes(0);
            } else {
                dataSet.get(position).setSemiFinalWinner2("");
                dataSet.get(position).setSemiFinalWinner2Res(0);
            }
            removeFinalData();
        }
    }

    private void removeFinalData() {
        int size = dataSet.size();
        dataSet.subList(19, dataSet.size()).clear();
        notifyItemRangeRemoved(19, size - 19);
    }

    private void addFinalData() {
        dataSet.add(new Team());
        dataSet.add(getFinalTeams());
        notifyItemRangeInserted(19, 2);
        listener.onOtherViewAdded();
    }

    private Team getFinalTeams() {
        return new Team("Final",
                dataSet.get(18).getSemiFinalWinner(), dataSet.get(18).getSemiFinalWinner2(),
                dataSet.get(18).getSemiFinalWinnerRes(), dataSet.get(18).getSemiFinalWinner2Res());
    }

    private void bindRound8Buttons(final ViewHolder holder) {
        if (dataSet.get(holder.getAdapterPosition()).getRound8Winner().equals("")) {
            resetWinner(holder.btn1, holder.btn2);
        }
        if (dataSet.get(holder.getAdapterPosition()).getRound8Winner2().equals("")) {
            resetWinner(holder.btn3, holder.btn4);
        }
        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRound8UI(holder.btn1, holder.btn2, holder, 1);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRound8UI(holder.btn2, holder.btn1, holder, 1);
            }
        });
        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRound8UI(holder.btn3, holder.btn4, holder, 2);
            }
        });
        holder.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRound8UI(holder.btn4, holder.btn3, holder, 2);
            }
        });
    }

    private void bindRound16Buttons(final ViewHolder holder) {
        if (dataSet.get(holder.getAdapterPosition()).getRound16Winner().equals("")) {
            resetWinner(holder.btn1, holder.btn2);
        }
        if (dataSet.get(holder.getAdapterPosition()).getRound16Winner2().equals("")) {
            resetWinner(holder.btn3, holder.btn4);
        }
        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRound16UI(holder.btn1, holder.btn2, holder, 1);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRound16UI(holder.btn2, holder.btn1, holder, 1);
            }
        });
        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRound16UI(holder.btn3, holder.btn4, holder, 2);
            }
        });
        holder.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRound16UI(holder.btn4, holder.btn3, holder, 2);
            }
        });
    }

    private void updateRound8UI(Button btn1, Button btn2, ViewHolder holder, int section) {
        int position = holder.getAdapterPosition();
        if (btn1.getText().toString().equals("+")) {
            setWinner(btn1, btn2);
            if (section == 1) {
                dataSet.get(position).setRound8Winner(Util.getTeamByButton(btn1, holder));
                dataSet.get(position).setRound8WinnerRes(Util.getFlagByButton(btn1, holder));
            } else {
                dataSet.get(position).setRound8Winner2(Util.getTeamByButton(btn1, holder));
                dataSet.get(position).setRound8Winner2Res(Util.getFlagByButton(btn1, holder));
            }
            if (checkIfRound8Done()) {
                addSemiFinalData();
            }
        } else if (btn1.getText().toString().equals("WIN")) {
            resetWinner(btn1, btn2);
            if (section == 1) {
                dataSet.get(position).setRound8Winner("");
                dataSet.get(position).setRound8WinnerRes(0);
            } else {
                dataSet.get(position).setRound8Winner2("");
                dataSet.get(position).setRound8Winner2Res(0);
            }
            removeSemiFinalData();
        }
    }

    private void removeSemiFinalData() {
        int size = dataSet.size();
        dataSet.subList(17, dataSet.size()).clear();
        notifyItemRangeRemoved(17, size - 17);
    }

    private void updateRound16UI(Button btn1, Button btn2, ViewHolder holder, int section) {
        int position = holder.getAdapterPosition();
        if (btn1.getText().toString().equals("+")) {
            setWinner(btn1, btn2);
            if (section == 1) {
                dataSet.get(position).setRound16Winner(Util.getTeamByButton(btn1, holder));
                dataSet.get(position).setRound16WinnerRes(Util.getFlagByButton(btn1, holder));
            } else {
                dataSet.get(position).setRound16Winner2(Util.getTeamByButton(btn1, holder));
                dataSet.get(position).setRound16Winner2Res(Util.getFlagByButton(btn1, holder));
            }
            if (checkIfRound16Done()) {
                addRound8Data();
            }
        } else if (btn1.getText().toString().equals("WIN")) {
            resetWinner(btn1, btn2);
            if (section == 1) {
                dataSet.get(position).setRound16Winner("");
                dataSet.get(position).setRound16WinnerRes(0);
            } else {
                dataSet.get(position).setRound16Winner2("");
                dataSet.get(position).setRound16Winner2Res(0);
            }
            remove8Round();
        }
    }

    private void setWinner(Button btn1, Button btn2) {
        btn1.setText("WIN");
        btn1.setBackground(ContextCompat.getDrawable(context, R.drawable.buttonshape));
        btn2.setText("Out");
        btn2.setBackground(ContextCompat.getDrawable(context, R.drawable.red_shape));
    }

    private void resetWinner(Button btn1, Button btn2) {
        btn1.setText("+");
        btn1.setBackground(ContextCompat.getDrawable(context, R.drawable.buttonshape));
        btn2.setText("+");
        btn2.setBackground(ContextCompat.getDrawable(context, R.drawable.buttonshape));
    }

    private void bindGroupButtons(final ViewHolder holder) {
        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (unmarkGroups(holder.btn1, holder.btn2, holder.btn3, holder.btn4, holder)) {
                    return;
                }
                updateGroupUI(holder.btn1, holder);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unmarkGroups(holder.btn2, holder.btn1, holder.btn3, holder.btn4, holder))
                    return;
                updateGroupUI(holder.btn2, holder);
            }
        });
        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unmarkGroups(holder.btn3, holder.btn2, holder.btn1, holder.btn4, holder))
                    return;
                updateGroupUI(holder.btn3, holder);
            }
        });
        holder.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unmarkGroups(holder.btn4, holder.btn2, holder.btn3, holder.btn1, holder))
                    return;
                updateGroupUI(holder.btn4, holder);
            }
        });
    }

    private boolean checkIfRound16Done() {
        for (int i = 10; i < 14; i++) {
            if (dataSet.get(i).getRound16Winner().equals("")) return false;
            if (dataSet.get(i).getRound16Winner2().equals("")) return false;
        }
        return true;
    }

    private boolean checkIfRound8Done() {
        for (int i = 15; i < 17; i++) {
            if (dataSet.get(i).getRound8Winner().equals("")) return false;
            if (dataSet.get(i).getRound8Winner2().equals("")) return false;
        }
        return true;
    }

    private boolean checkIfSemiFinalDone() {
        if (dataSet.get(18).getSemiFinalWinner().equals("")) return false;
        if (dataSet.get(18).getSemiFinalWinner2().equals("")) return false;
        return true;
    }

    private boolean checkIfAllGroupsDone() {
        for (int i = 1; i < GROUP_COUNT + 1; i++) {
            if (!dataSet.get(i).isDone()) return false;
        }
        return true;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private Team getRound8Teams(int pos1, int pos2) {
        return new Team("Quarter-final", dataSet.get(pos1).getRound16Winner(), dataSet.get(pos2).getRound16Winner(),
                dataSet.get(pos1).getRound16Winner2(), dataSet.get(pos2).getRound16Winner2(),
                dataSet.get(pos1).getRound16WinnerRes(), dataSet.get(pos2).getRound16WinnerRes(),
                dataSet.get(pos1).getRound16Winner2Res(), dataSet.get(pos2).getRound16Winner2Res());
    }

    private Team getRound16Teams(int pos1, int pos2) {
        return new Team("Round 16", dataSet.get(pos1).getFirstPlace(), dataSet.get(pos2).getSecondPlace(),
                dataSet.get(pos1).getSecondPlace(), dataSet.get(pos2).getFirstPlace(),
                dataSet.get(pos1).getFirstPlaceRes(), dataSet.get(pos2).getSecondPlaceRes(),
                dataSet.get(pos1).getSecondPlaceRes(), dataSet.get(pos2).getFirstPlaceRes());
    }

    private Team getSemiFinalTeams(int pos1, int pos2) {
        return new Team("Semi-final", dataSet.get(pos1).getRound8Winner(), dataSet.get(pos2).getRound8Winner(),
                dataSet.get(pos1).getRound8Winner2(), dataSet.get(pos2).getRound8Winner2(),
                dataSet.get(pos1).getRound8WinnerRes(), dataSet.get(pos2).getRound8WinnerRes(),
                dataSet.get(pos1).getRound8Winner2Res(), dataSet.get(pos2).getRound8Winner2Res());
    }

    private void addSemiFinalData() {
        dataSet.add(new Team());
        dataSet.add(getSemiFinalTeams(15, 16));
        notifyItemRangeInserted(17, 2);
        listener.onOtherViewAdded();
    }

    private void addRound8Data() {
        dataSet.add(new Team());
        dataSet.add(getRound8Teams(10, 11));
        dataSet.add(getRound8Teams(12, 13));
        notifyItemRangeInserted(14, 3);
        listener.onOtherViewAdded();
    }

    private void addRound16Data() {
        dataSet.add(new Team());
        dataSet.add(getRound16Teams(1, 2));
        dataSet.add(getRound16Teams(3, 4));
        dataSet.add(getRound16Teams(5, 6));
        dataSet.add(getRound16Teams(7, 8));
        notifyItemRangeInserted(9, 5);
        notifyItemChanged(10);
        notifyItemChanged(11);
        notifyItemChanged(12);
        notifyItemChanged(13);
    }

    private void updateGroupUI(Button btn, ViewHolder holder) {
        int position = holder.getAdapterPosition();
        String which;
        if (holder.isSecond) {
            which = "2nd";
            btn.setText(which);
            holder.isSecond = false;
            markOuts(holder);
            holder.informer.setText("Done");
            dataSet.get(position).setDone(true);
            String teamName = Util.getTeamByButton(btn, holder);
            dataSet.get(position).setSecondPlace(teamName);
            int countryFlag = Util.getFlagByButton(btn, holder);
            dataSet.get(position).setSecondPlaceRes(countryFlag);
            Log.d("teamName2", teamName);

            if (checkIfAllGroupsDone()) {
                addRound16Data();
            }
            return;
        }
        if (holder.isFirst) {
            which = "1st";
            btn.setText(which);
            holder.isFirst = false;
            holder.isSecond = true;
            holder.informer.setText("Select 2nd place");
            String teamName = Util.getTeamByButton(btn, holder);
            dataSet.get(position).setFirstPlace(teamName);
            int countryFlag = Util.getFlagByButton(btn, holder);
            dataSet.get(position).setFirstPlaceRes(countryFlag);
            dataSet.get(position).setDone(false);
            Log.d("teamName", teamName);
        }
    }

    private boolean unmarkGroups(Button btnClicked, Button btn2, Button btn3, Button btn4, ViewHolder holder) {
        int position = holder.getAdapterPosition();

        if (!btnClicked.getText().equals("+") && !btnClicked.getText().equals("Out")) {
            if (unmarkIfClickedOn2nd(btnClicked, btn2, btn3, btn4, holder)) return true;
            if (unmarkIfClickedOn2nd(btnClicked, btn3, btn2, btn4, holder)) return true;
            if (unmarkIfClickedOn2nd(btnClicked, btn4, btn2, btn3, holder)) return true;

            if (unmarkIfClickedOn1stWhenThereIs2nd(btnClicked, btn2, btn3, btn4, holder))
                return true;
            if (unmarkIfClickedOn1stWhenThereIs2nd(btnClicked, btn3, btn2, btn4, holder))
                return true;
            if (unmarkIfClickedOn1stWhenThereIs2nd(btnClicked, btn4, btn3, btn2, holder))
                return true;

            btn2.setText("+");
            restoreButtons(btnClicked, btn2, btn3, btn4);
            holder.isFirst = true;
            holder.isSecond = false;
            holder.informer.setText("Select 1st place");
            dataSet.get(position).setDone(false);
            remove16Round();
            return true;
        }
        return false;
    }

    private boolean unmarkIfClickedOn1stWhenThereIs2nd(Button btnClicked, Button btnOther1, Button btnOther2, Button btnOther3, ViewHolder holder) {
        if (btnClicked.getText().equals("1st")) {
            if (btnOther1.getText().equals("2nd")) {
                int position = holder.getAdapterPosition();
                btnOther1.setText("1st");
                restoreButtons(btnClicked, btnOther1, btnOther2, btnOther3);
                holder.isFirst = false;
                holder.isSecond = true;
                holder.informer.setText("Select 2nd place");
                dataSet.get(position).setDone(false);
                dataSet.get(position).setFirstPlace(Util.getTeamByButton(btnOther1, holder));
                dataSet.get(position).setFirstPlaceRes(Util.getFlagByButton(btnOther1, holder));
                dataSet.get(position).setSecondPlace("");
                dataSet.get(position).setSecondPlaceRes(0);

                remove16Round();
                return true;
            }
        }
        return false;
    }


    private boolean unmarkIfClickedOn2nd(Button btnClicked, Button btnOther1,
                                         Button btnOther2, Button btnOther3, ViewHolder holder) {
        if (btnOther1.getText().equals("1st")) {
            int position = holder.getAdapterPosition();
            restoreButtons(btnClicked, btnOther1, btnOther2, btnOther3);
            holder.isSecond = true;
            holder.isFirst = false;
            holder.informer.setText("Select 2nd place");
            dataSet.get(position).setDone(false);
            dataSet.get(position).setSecondPlace("");
            dataSet.get(position).setSecondPlaceRes(0);
            remove16Round();
            return true;
        }
        return false;
    }

    private void remove8Round() {
        if (14 != dataSet.size()) {
            int size = dataSet.size();
            dataSet.subList(14, dataSet.size()).clear();
            notifyItemRangeRemoved(14, size - 14);
        }
    }

    private void remove16Round() {
        int size = dataSet.size();
        dataSet.subList(9, size).clear();
        notifyItemRangeRemoved(9, size - 9);

    }

    private void restoreButtons(Button btnClicked, Button btnOther1, Button btnOther2, Button btnOther3) {
        btnClicked.setText("+");
        btnOther2.setText("+");
        btnOther3.setText("+");
        btnClicked.setBackground(ContextCompat.getDrawable(context, R.drawable.buttonshape));
        btnOther1.setBackground(ContextCompat.getDrawable(context, R.drawable.buttonshape));
        btnOther2.setBackground(ContextCompat.getDrawable(context, R.drawable.buttonshape));
        btnOther3.setBackground(ContextCompat.getDrawable(context, R.drawable.buttonshape));
    }

    private void markOuts(ViewHolder holder) {
        if (holder.btn1.getText().equals("+")) {
            holder.btn1.setText("Out");
            holder.btn1.setBackground(ContextCompat.getDrawable(context, R.drawable.red_shape));
        }
        if (holder.btn2.getText().equals("+")) {
            holder.btn2.setText("Out");
            holder.btn2.setBackground(ContextCompat.getDrawable(context, R.drawable.red_shape));
        }
        if (holder.btn3.getText().equals("+")) {
            holder.btn3.setText("Out");
            holder.btn3.setBackground(ContextCompat.getDrawable(context, R.drawable.red_shape));
        }
        if (holder.btn4.getText().equals("+")) {
            holder.btn4.setText("Out");
            holder.btn4.setBackground(ContextCompat.getDrawable(context, R.drawable.red_shape));
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
