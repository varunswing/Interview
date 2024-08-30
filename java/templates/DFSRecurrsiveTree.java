public static void dfs(TreeNode root) {
    if (root == null) return;

    System.out.print(root.val + " ");
    dfs(root.left);
    dfs(root.right);
}