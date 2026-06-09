import numpy as np


def layer_norm(x, g, b, eps=1e-5):
    mean = np.mean(x, axis=-1, keepdims=True)
    var = np.var(x, axis=-1, keepdims=True)
    return g * (x - mean) / np.sqrt(var + eps) + b


def softmax(x):
    x = x - np.max(x, axis=-1, keepdims=True)
    exp_x = np.exp(x)
    return exp_x / np.sum(exp_x, axis=-1, keepdims=True)

def feed_forward(x):
    return gelu(x)

def gelu(x):
    return 0.5 * x * (
            1 +
            np.tanh(
                np.sqrt(2 / np.pi) *
                (x + 0.044715 * x ** 3)
            )
    )

def attention(x):

    scores = x @ x.T

    scores /= np.sqrt(x.shape[-1])

    mask = (1 - np.tri(x.shape[0], dtype=x.dtype)) * -1e10

    scores += mask

    attn = softmax(scores)

    return attn @ x


def gen_text(prompt: str, n_tokens_to_generate: int = 40):
    encoder, hparams, params = load_encoder_hparams_and_params()
    token_ids = encoder.encode(prompt)
    for _ in range(n_tokens_to_generate):
        seq_len = len(token_ids)
        token_emb = params["wte"][token_ids]
        pos_emb = params["wpe"][:seq_len]
        x = token_emb + pos_emb

        # =======多头注意力==========
        attn_out = attention(x)

        x = x + attn_out


        x = layer_norm(x, params["ln_f"]["g"], params["ln_f"]["b"])

        # ===== FFN =====

        ffn_out = feed_forward(x)

        x = x + ffn_out

        #======= Rediual + layerNorm
        x = layer_norm(
            x,
            params["ln_f"]["g"],
            params["ln_f"]["b"]
        )

        #  ======输出层========
        # 使用最后一个token的隐藏状态
        last_hidden = x[-1]

        # GPT-2输出层与词嵌入共享权重
        logits = last_hidden @ params["wte"].T
        next_token = np.argmax(logits)
        token_ids.append(int(next_token))
    return encoder.decode(token_ids)


def load_encoder_hparams_and_params(model_size: str = "124M", models_dir: str = "models"):
    np.random.seed(0)
    class DummyBPE:
        def __init__(self):
            self.encoder_dict = {"hello": 1, "world": 2, "<UNK>": 0}

        def encode(self, text: str):
            tokens = text.strip().split()
            return [self.encoder_dict.get(token, self.encoder_dict["<UNK>"]) for token in tokens]

        def decode(self, token_ids: list):
            reversed_dict = {v: k for k, v in self.encoder_dict.items()}
            return " ".join([reversed_dict.get(tok_id, "<UNK>") for tok_id in token_ids])

    hparams = {
        "n_ctx": 1024,
        "n_head": 12
    }

    params = {
        "wte": np.random.rand(3, 10),
        "wpe": np.random.rand(1024, 10),
        "blocks": [],
        "ln_f": {
            "g": np.ones(10),
            "b": np.zeros(10),
        }
    }

    encoder = DummyBPE()
    return encoder, hparams, params


# 主程序
if __name__ == "__main__":
    # 输入
    prompt = input()
    n_tokens_to_generate = int(input())

    # 调用函数
    output = gen_text(prompt, n_tokens_to_generate)

    # 输出结果
    print(output)
