package com.wedt.metric;

import com.wedt.app.ReadStringSetFromFile;
import com.wedt.model.FBPost;
import com.wedt.model.FBPostKind;

import java.io.IOException;
import java.util.Set;

public class PostKindCalculator {

    public static FBPostKind calculatePostKind(FBPost post) {

        String msg = post.getMsg();
        Set<String> dictKlient = null;
        Set<String> dictMuzyk = null;

        try {
            dictKlient = ReadStringSetFromFile.getDict("klient.txt");
        } catch (IOException e) {
            // TODO
        }
        try {
            dictMuzyk = ReadStringSetFromFile.getDict("muzyk.txt");
        } catch (IOException e) {
            // TODO
        }

        int howManyInKlient = 0;
        int howManyInMuzyk = 0;

        if (dictKlient != null && dictKlient.size() > 0) {
            for (String word : dictKlient) {
                if (msg.contains(word)) howManyInKlient++;
            }
        }

        if (dictMuzyk != null && dictMuzyk.size() > 0) {
            for (String word : dictMuzyk) {
                if (msg.contains(word)) howManyInMuzyk++;
            }
        }

        if (howManyInKlient > howManyInMuzyk) {
            return FBPostKind.OFFER;
        } else if (howManyInMuzyk > howManyInKlient) {
            return FBPostKind.PROFILE;
        } else {
            return FBPostKind.UNKNOWN;
        }
    }
}
