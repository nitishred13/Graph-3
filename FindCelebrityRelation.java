
//Time Complexity:O(n)
//Space Complexity:O(1)
//We assume person 0 is the celebrity and update that guess whenever they know someone else.
//After finding the candidate, we check if everyone knows them and they know no one.
//If any condition fails, return -1 — otherwise, return the candidate.
public class FindCelebrityRelation {
    public int findCelebrity(int n){
        int ptnlCand = 0;
        for(int i=1;i<n;i++){  
            if(knows(ptnlCand,i))
            {
                ptnlCand = i;
            }
        }

        for(int i=0;i<n;i++)
        {
            if(i==ptnlCand) continue;
            if(knows(ptnlCand,i)||!knows(i,ptnlCand)) return -1;
        }
        return ptnlCand;
    }
}
