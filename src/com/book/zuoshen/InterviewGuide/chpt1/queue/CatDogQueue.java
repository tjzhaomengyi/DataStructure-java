package com.book.zuoshen.InterviewGuide.chpt1.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/11 12:03
 * @Description:
 */
public class CatDogQueue {
    public class Pet {
        private String type;

        public Pet(String type){
            this.type = type;
        }

        public String getPetType(){
            return this.type;
        }
    }

    public class Dog extends Pet {
        public Dog(){
            super("dog");
        }
    }

    public class Cat extends Pet {
        public Cat(){
            super("cat");
        }
    }

    //这个是队列元素
    public class PetEnterQueue{
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count){
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet(){
            return this.pet;
        }

        public long getCount(){
            return this.count;
        }

        public String getEnterPetType() {
            return this.pet.getPetType();
        }
    }

        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private long count;

        public CatDogQueue(){
            this.dogQ = new LinkedList<PetEnterQueue>();
            this.catQ = new LinkedList<PetEnterQueue>();
            this.count = 0;
        }

        public void add(Pet pet){
            if(pet.getPetType().equals("dog")){
                this.dogQ.add(new PetEnterQueue(pet, this.count++));
            } else if (pet.getPetType().equals("cat")){
                this.catQ.add(new PetEnterQueue(pet, this.count++));
            } else {
                throw new RuntimeException("err, not dog or cat");
            }
        }

        public Pet pollAll(){
            if(!this.dogQ.isEmpty() && !this.catQ.isEmpty()){
                if(this.dogQ.peek().getCount() < this.catQ.peek().getCount()){
                    return this.dogQ.poll().getPet();
                } else {
                    return this.catQ.poll().getPet();
                }
            } else if(!this.dogQ.isEmpty()){
                return this.dogQ.poll().getPet();
            } else if(!this.catQ.isEmpty()){
                return this.catQ.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty");
            }
        }

        public Dog pollDog(){
            if(!this.isDogQueueEmpty()){
                return (Dog) this.dogQ.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty");
            }
        }

        public Cat pollCat(){
            if(!this.isCatQueueEmpty()){
                return (Cat) this.catQ.poll().getPet();
            } else {
                throw new RuntimeException("Cat queue is empty");
            }
        }

        public boolean isEmpty(){
            return this.dogQ.isEmpty() && this.catQ.isEmpty();
        }

        public boolean isDogQueueEmpty(){
            return this.dogQ.isEmpty();
        }

        public boolean isCatQueueEmpty(){
            return this.catQ.isEmpty();
        }
}






