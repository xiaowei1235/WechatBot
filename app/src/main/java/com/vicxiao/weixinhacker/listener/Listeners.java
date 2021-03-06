package com.vicxiao.weixinhacker.listener;

import com.vicxiao.weixinhacker.message.AudioMessage;
import com.vicxiao.weixinhacker.message.Message;
import com.vicxiao.weixinhacker.message.TextMessage;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by vic on 15-12-3.
 */
public class Listeners {
    private static Set<TextMessageListener> textMessageListeners = new CopyOnWriteArraySet<>();
    private static Set<AudioMessageListener> audioMessageListeners = new CopyOnWriteArraySet<>();

    private static Set<EventListener> eventListeners = new CopyOnWriteArraySet<>();

    public static void addEventListener(EventListener listener) {
        eventListeners.add(listener);
    }

    public static void removeEventListener(EventListener listener) {
        eventListeners.remove(listener);
    }


    //TODO:
    // Change message listener style to be like event listener.
    public static void addTextMessageListener(TextMessageListener listener) {
        textMessageListeners.add(listener);
    }

    public static void removeTextMessageListener(TextMessageListener listener) {
        textMessageListeners.remove(listener);
    }


    public static void addAudioMessageListener(AudioMessageListener listener){
        audioMessageListeners.add(listener);
    }

    public static void removeAudioMessageListener(AudioMessageListener listener){
        audioMessageListeners.remove(listener);
    }

    public static void handleNewTextMessage(TextMessage message) {
        for (TextMessageListener textMessageListener : textMessageListeners) {
            textMessageListener.onNewMessage(message);
        }
    }

    public static void handleNewAudioMessage(AudioMessage message){
        for (AudioMessageListener audioMessageListener : audioMessageListeners) {
            audioMessageListener.onNewMessage(message);
        }
    }

    public static void handleNewEvent(Event event) {
        switch (event.type) {
            case NEW_MEMBER:
                for (EventListener eventListener : eventListeners) {
                    eventListener.onNewMember(event);
                }
                break;
        }
    }
}
