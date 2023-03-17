/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.hippo4j.springboot.starter.core;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.Getter;

/**
 * Client Shutdown
 */
public class ClientShutdown {

    @Getter
    private volatile boolean prepareClose = false;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private final static Long TIME_OUT_SECOND = 1L;

    public void prepareDestroy() throws InterruptedException {
        prepareClose = true;
        countDownLatch.await(TIME_OUT_SECOND, TimeUnit.SECONDS);
    }

    public void countDown() {
        countDownLatch.countDown();
    }

}