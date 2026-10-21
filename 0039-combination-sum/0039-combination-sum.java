import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    void solve(int[] arr, int target, int index,
               List<Integer> temp, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        if (index == arr.length || target < 0) {
            return;
        }

        temp.add(arr[index]);
        solve(arr, target - arr[index], index, temp, ans);

        temp.remove(temp.size() - 1);
        solve(arr, target, index + 1, temp, ans);
    }
}