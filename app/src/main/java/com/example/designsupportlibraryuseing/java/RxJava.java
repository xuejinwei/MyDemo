package com.example.designsupportlibraryuseing.java;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 函数相应式编程Demo
 * Created by 晋伟 on 2015/8/27 0027.
 */
public class RxJava {

    public static void main(String args[]) {
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );


        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        myObservable.subscribe(mySubscriber);

        //-----------------------------

        Observable<String> myObservable01 = Observable.just("M星空");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        myObservable01.subscribe(onNextAction);
        //-----------------------------
        Observable.just("简洁……").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        //-----------------------------


    }
}
