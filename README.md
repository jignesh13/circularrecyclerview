# strangegridlayout
<image src=https://user-images.githubusercontent.com/20221469/56945920-5c2f5780-6b46-11e9-8284-2dd274a0333f.gif width=225 height=400>

***control in your code***
```java
  RecyclerView recyclerView=findViewById(R.id.recyclerview);
  StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
  recyclerView.setLayoutManager(staggeredGridLayoutManager);
  recyclerView.setHasFixedSize(true);
  recyclerView.setAdapter(new MyAdapter());
```
***recyclerview adapter implementation***
```java
public int getItemViewType(int position)
        {
            if (position==0||position==2)
            {
                return 1;

            }
            else if (position==1)
            {
                return 2;
            }
            else
            {
                return 3;
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.e("call","call");
            if (viewType==1)
            {
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.emptypeopleview, null);
                EmptyViewHolder rcv = new EmptyViewHolder(layoutView);
                return rcv;
            }
            else if (viewType==2)
            {
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.avatar_head, null);
                AvatarViewHolder rcv = new AvatarViewHolder(layoutView);
                return rcv;
            }
            else
            {
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userviewholder, null);
                PeopleViewHolder rcv = new PeopleViewHolder(layoutView);
                return rcv;
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof PeopleViewHolder)
            {
                if ((position+1)%3==0)
                {
                    PeopleViewHolder peopleViewHolder= (PeopleViewHolder) holder;
                    peopleViewHolder.username.setText(data.get(position).getName());
                    Glide.with(GifActivity.this).load(data.get(position).getProfilepic()).apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.drawable.userplaceholder)).into(((PeopleViewHolder) holder).profileimageView);
                }
                else
                {

                    PeopleViewHolder peopleViewHolder= (PeopleViewHolder) holder;
                    peopleViewHolder.username.setText(data.get(position).getName());
                    Glide.with(GifActivity.this).load(data.get(position).getProfilepic()).apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.drawable.userplaceholder)).into(((PeopleViewHolder) holder).profileimageView);

                }


            }
            setAnimation(holder,position);
        }
   ```     
***animation change while item present***
```java
 protected void setAnimation(RecyclerView.ViewHolder holder, int position) {
 ///anim_slide_from_bottom file contain animation

            if (this.animatedPosition.contains(Integer.valueOf(position))) {
                holder.itemView.clearAnimation();
                return;
            }
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_slide_from_bottom));
            this.animatedPosition.add(Integer.valueOf(position));
        }
```

##  Developer
  jignesh khunt
  (jigneshkhunt13@gmail.com)
  
##  License

Copyright 2019 jignesh khunt

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
