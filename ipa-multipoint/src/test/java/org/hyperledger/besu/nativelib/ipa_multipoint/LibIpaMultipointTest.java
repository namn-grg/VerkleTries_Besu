/*
 * Copyright Besu Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 */
package org.hyperledger.besu.nativelib.ipa_multipoint;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.bytes.Bytes32;
import org.hyperledger.besu.nativelib.ipamultipoint.LibIpaMultipoint;


public class LibIpaMultipointTest {

    @Test
    public void testCallLibrary() {
        Bytes32 input = Bytes32.fromHexString("0x0cfe0000");
        Bytes32 result = Bytes32.wrap(LibIpaMultipoint.commit(input.toArray()));
        Bytes32 expected = Bytes32.fromHexString("0xb969597c3035c73d5a3eaa340672265844c48e7ed4802f76a15a555c1e3b7511");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCallLibraryWithManyElements() {
        Bytes32 element = Bytes32.fromHexString("0x0cfe3041fb6512c87922e2146c8308b372f3bf967f889e69ad116ce7c7ec00");
        Bytes32[] arr = new Bytes32[128];
        for (int i = 0 ; i < 128; i++) {
            arr[i] = element;
        }
        Bytes input = Bytes.concatenate(arr);
        Bytes32 result = Bytes32.wrap(LibIpaMultipoint.commit(input.toArray()));
        Bytes32 expected = Bytes32.fromHexString("0x270c98d8e7c80c5d179fc631a34d4f8d94276bf08781a36531fbf9fb01b48509");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCallLibraryWithMaxElements() {
        Bytes32 element = Bytes32.fromHexString("0xd36f20567f74f607d9252186ff8efed04de4578d1ddb3de4fe6c5e4249e0045b");
        Bytes32[] arr = new Bytes32[256];
        for (int i = 0 ; i < 256; i++) {
            arr[i] = element;
        }
        Bytes input = Bytes.concatenate(arr);
        Bytes32 result = Bytes32.wrap(LibIpaMultipoint.commit(input.toArray()));
        Bytes32 expected = Bytes32.fromHexString("0x901f1fb216d7903987a1f021284dfdec5dc39c53617aacd4640bb8e81758ab06");
        assertThat(result).isEqualTo(expected);
    }
}
