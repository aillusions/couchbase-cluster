# couchbase-cluster

One terminal:
    cd provisioning/docker
    ./env.sh

Perform manual config of cluster    
    
Another terminal:
    cd couchbackend/docker
    ./env.sh



OR 


https://github.com/gruntwork-io/terraform-aws-couchbase

cd provisioning/terraform-aws-couchbase

export AWS_DEFAULT_REGION=eu-west-1

terraform init

terraform plan

terraform apply

Outputs:

   couchbase_cluster_asg_name = couchbase-server
   couchbase_web_console_url = couchbase-server-865422306.eu-west-1.elb.amazonaws.com:8091
   sync_gateway_url = couchbase-server-865422306.eu-west-1.elb.amazonaws.com:4984
      
      # To keep this example simple, we are hard-coding all credentials in this file in plain text. You should NOT do this
      # in production usage!!! Instead, you should use tools such as Vault, Keywhiz, or KMS to fetch the credentials at
      # runtime and only ever have the plaintext version in memory.
      local readonly cluster_username="admin"
      local readonly cluster_password="password"
      local readonly test_user_name="test-user"
      local readonly test_user_password="password"
      local readonly test_bucket_name="test-bucket"

terraform destroy
