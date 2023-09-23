package me.necrosis.fwc.plugins.event;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.EventBus;

/**
 * <h1>EventBus plugin</h1>
 * <br><br>
 * EventBusPlugin uses {@code greenrobot/EventBus} dependency. <br>
 * For more info read <a href="https://github.com/greenrobot/EventBus">github.com/greenrobot/EventBus</a>.<br>
 * <br><br>
 *
 * <h2>Define events:</h2>
 * <div>
 *     <pre style="margin: 0; line-height: 125%"><span style="color: #6ab825; font-weight: bold">public</span> <span style="color: #6ab825; font-weight: bold">static</span> <span style="color: #6ab825; font-weight: bold">class</span> <span style="color: #447fcf; text-decoration: underline">MessageEvent</span> <span style="color: #d0d0d0">{</span> <span style="color: #999999; font-style: italic">/ Additional fields if needed / </span><span style="color: #d0d0d0">}</span></pre>
 * </div>
 * <br>
 * <h2>Prepare subscribers: Declare and annotate your subscribing method, optionally specify a <a href="https://greenrobot.org/eventbus/documentation/delivery-threads-threadmode/">thread mode</a>:</h2>
 * <div><pre style="margin: 0; line-height: 125%"><span style="color: #ffa500">  @Subscribe</span><span style="color: #d0d0d0">(threadMode</span> <span style="color: #d0d0d0">=</span> <span style="color: #d0d0d0">ThreadMode.</span><span style="color: #bbbbbb">MAIN</span><span style="color: #d0d0d0">)</span>
 * <span style="color: #6ab825; font-weight: bold">public</span> <span style="color: #6ab825; font-weight: bold">void</span> <span style="color: #447fcf">onMessageEvent</span><span style="color: #d0d0d0">(MessageEvent</span> <span style="color: #d0d0d0">event)</span> <span style="color: #d0d0d0">{</span>
 *     <span style="color: #999999; font-style: italic">// Do something</span>
 * <span style="color: #d0d0d0">}</span>
 * </pre></div>
 * <p>Register and unregister your subscriber. For example on Android, activities and fragments should usually register according to their life cycle:</p>
 * <div><pre style="margin: 0; line-height: 125%"> <span style="color: #ffa500">  @Override</span>
 *  <span style="color: #6ab825; font-weight: bold">public</span> <span style="color: #6ab825; font-weight: bold">void</span> <span style="color: #447fcf">onStart</span><span style="color: #d0d0d0">()</span> <span style="color: #d0d0d0">{</span>
 *      <span style="color: #6ab825; font-weight: bold">super</span><span style="color: #d0d0d0">.</span><span style="color: #bbbbbb">onStart</span><span style="color: #d0d0d0">();</span>
 *      <span style="color: #d0d0d0">EventBus.</span><span style="color: #bbbbbb">getDefault</span><span style="color: #d0d0d0">().</span><span style="color: #bbbbbb">register</span><span style="color: #d0d0d0">(</span><span style="color: #6ab825; font-weight: bold">this</span><span style="color: #d0d0d0">);</span>
 *  <span style="color: #d0d0d0">}</span>
 *
 *  <span style="color: #ffa500">@Override</span>
 *  <span style="color: #6ab825; font-weight: bold">public</span> <span style="color: #6ab825; font-weight: bold">void</span> <span style="color: #447fcf">onStop</span><span style="color: #d0d0d0">()</span> <span style="color: #d0d0d0">{</span>
 *      <span style="color: #6ab825; font-weight: bold">super</span><span style="color: #d0d0d0">.</span><span style="color: #bbbbbb">onStop</span><span style="color: #d0d0d0">();</span>
 *      <span style="color: #d0d0d0">EventBus.</span><span style="color: #bbbbbb">getDefault</span><span style="color: #d0d0d0">().</span><span style="color: #bbbbbb">unregister</span><span style="color: #d0d0d0">(</span><span style="color: #6ab825; font-weight: bold">this</span><span style="color: #d0d0d0">);</span>
 *  <span style="color: #d0d0d0">}</span>
 * </pre></div>
 * <br>
 * <h2>Post events:</h2>
 * <div><pre style="margin: 0; line-height: 125%"> <span style="color: #d0d0d0">EventBus.</span><span style="color: #bbbbbb">getDefault</span><span style="color: #d0d0d0">().</span><span style="color: #bbbbbb">post</span><span style="color: #d0d0d0">(</span><span style="color: #6ab825; font-weight: bold">new</span> <span style="color: #d0d0d0">MessageEvent());</span>
 * </pre></div>
 */
@Slf4j
@Getter
public class EventBusPlugin {

    private final EventBus eventBus = EventBus.getDefault();

    public void registerListener(Object listener) {
        Preconditions.checkNotNull(listener);
        this.eventBus.register(listener);
    }

    public void registerListener(Object... listeners) {
        Preconditions.checkNotNull(listeners);
        for (Object listener : listeners) {
            registerListener(listener);
        }
    }

    public void unregisterListener(Object listener) {
        Preconditions.checkNotNull(listener);
        this.eventBus.unregister(listener);
    }

    public void unregisterListener(Object... listeners) {
        Preconditions.checkNotNull(listeners);
        for (Object listener : listeners) {
            this.unregisterListener(listener);
        }
    }

    public void postEvent(Object event) {
        this.eventBus.post(event);
    }

    public void postEvent(Object... events) {
        for (Object event : events) {
            postEvent(event);
        }
    }
}
