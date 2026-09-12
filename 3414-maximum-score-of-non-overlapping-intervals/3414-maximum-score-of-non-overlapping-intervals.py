class Solution(object):
    def maximumWeight(self, intervals):
        indexed_intervals = []
        for i, (l,r,w) in enumerate(intervals):
            indexed_intervals.append((l,r,w,i))
        
        indexed_intervals.sort(key=lambda x: (x[0], x[1], x[2], x[3]))
        n = len(indexed_intervals)
        starts = [x[0] for x in indexed_intervals]

        memo = {}

        def dp(i, quota):
            if i == n or quota == 0:
                return (0, [])

            if (i, quota) in memo:
                return memo[(i, quota)]

            w1, idx1 = dp(i + 1, quota)

            l_i, r_i, weight_i, id_i = indexed_intervals[i]

            next_idx = bisect.bisect_right(starts, r_i)
            w2, idx2 = dp(next_idx, quota -1)

            w2, idx2 = dp(next_idx, quota - 1)

            w2_total = weight_i + w2

            idx2_total = sorted([id_i] + idx2)

            if w1 > w2_total:
                res = (w1, idx1)
            elif w2_total > w1:
                res = (w2_total, idx2_total)
                
            else:
                if not idx1:
                    res = (w2_total, idx2_total)
                elif not idx2_total:
                    res = (w1, idx1)
                else:
                    res = (w1, min(idx1, idx2_total))
                
            memo[(i, quota)] = res
            return res
        return dp(0,4)[1]
            