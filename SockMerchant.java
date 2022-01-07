 public static int sockMerchant(int n, List<Integer> ar) {
            
            int socks = 0; 
            int sockPairs = 0; 
            
            while(ar.size() > 0) {
                int compare = ar.remove(0);
                socks = 1; 
                
                for(int i = 0; i < ar.size(); i++) {
                    if(ar.get(i) == compare) {
                        socks++; 
                        ar.remove(i);
                    }
                }
                if(socks == 1) {
                    sockPairs += 0; 
                }
                else{
                    sockPairs += socks;
                }
            }
            sockPairs = sockPairs/2;
            return sockPairs; 
    }
}